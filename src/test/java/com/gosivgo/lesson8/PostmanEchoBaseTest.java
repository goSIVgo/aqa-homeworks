package com.gosivgo.lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.*;

public class PostmanEchoBaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    protected void validateCommonResponseFields(io.restassured.response.Response response) {
        response.then()
                .statusCode(200)
                .body("url", startsWith("https://postman-echo.com/"))
                .body("headers", notNullValue())
                .body("headers.host", equalTo("postman-echo.com"));
    }
}