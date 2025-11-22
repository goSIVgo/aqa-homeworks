package com.gosivgo.lesson8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PatchRequestTest extends PostmanEchoBaseTest {

    @Test
    public void testPatchRequestWithTextBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("headers", notNullValue())
                .body("headers.content-type", containsString("text/plain"))
                .extract()
                .response();

        validateCommonResponseFields(response);
    }

    @Test
    public void testPatchRequestWithJsonBody() {
        String requestBody = "{\"patch\": \"test\", \"value\": 123}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data.patch", equalTo("test"))
                .body("data.value", equalTo(123))
                .body("json.patch", equalTo("test"))
                .body("json.value", equalTo(123));
    }
}