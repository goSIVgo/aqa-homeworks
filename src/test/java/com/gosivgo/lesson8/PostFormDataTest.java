package com.gosivgo.lesson8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostFormDataTest extends PostmanEchoBaseTest {

    @Test
    public void testPostFormDataMultipart() {
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }
}