package com.gosivgo.lesson8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest {

    @Test
    public void testDeleteRequestWithTextBody() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }
}