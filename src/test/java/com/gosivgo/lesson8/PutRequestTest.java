package com.gosivgo.lesson8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PutRequestTest extends PostmanEchoBaseTest {

    @Test
    public void testPutRequestWithTextBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("headers", notNullValue())
                .body("headers.content-type", containsString("text/plain"))
                .extract()
                .response();

        validateCommonResponseFields(response);
    }

    @Test
    public void testPutRequestWithJsonBody() {
        String requestBody = "{\"put\": \"test\", \"value\": 123}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data.put", equalTo("test"))
                .body("data.value", equalTo(123))
                .body("json.put", equalTo("test"))
                .body("json.value", equalTo(123));
    }
}