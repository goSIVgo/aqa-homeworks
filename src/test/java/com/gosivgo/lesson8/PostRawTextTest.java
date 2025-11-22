package com.gosivgo.lesson8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRawTextTest extends PostmanEchoBaseTest {

    @Test
    public void testPostRawTextWithJson() {
        String requestBody = "{\"test\": \"value\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data.test", equalTo("value"))
                .body("json", notNullValue())
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers", notNullValue())
                .body("headers.content-type", containsString("application/json"))
                .extract()
                .response();

        validateCommonResponseFields(response);
    }

    @Test
    public void testPostRawTextWithPlainText() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers.content-type", containsString("text/plain"));
    }
}