package com.arash.edu.bujournal.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class ApplicationControllerTest {

    @Value("http://localhost:${server.port}/v1/app")
    private String baseUrl;

    @Test
    void testGetStats() {
        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
        .when()
                .get(baseUrl + "/stats").prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on
    }
}
