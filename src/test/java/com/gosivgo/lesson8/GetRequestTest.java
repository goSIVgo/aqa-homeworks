package com.gosivgo.lesson8;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTest extends PostmanEchoBaseTest {

    @Test
    public void testGetRequestWithQueryParameters() {
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"))
                .body("headers", notNullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .extract()
                .response();

        validateCommonResponseFields(response);
    }

    @Test
    public void testGetRequestWithoutParameters() {
        given()
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args", equalTo(new java.util.LinkedHashMap<>()))
                .body("url", equalTo("https://postman-echo.com/get"))
                .body("headers", notNullValue());
    }
}