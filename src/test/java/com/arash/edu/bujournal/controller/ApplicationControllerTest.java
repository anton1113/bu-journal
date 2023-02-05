package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.BaseIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ApplicationControllerTest extends BaseIntegrationTest {

    @Value("http://localhost:${local.server.port}/v1/app")
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
