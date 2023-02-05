package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.BaseIntegrationTest;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.repository.TeacherRepository;
import com.arash.edu.bujournal.util.JsonUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.arash.edu.bujournal.util.TestDataProvider.createTeacher;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherControllerTest extends BaseIntegrationTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Value("http://localhost:${local.server.port}/v1/teachers")
    private String baseUrl;

    @BeforeEach
    void beforeEach() {
        teacherRepository.deleteAll();
    }

    @Test
    void testGetTeacher() {
        Teacher teacher = teacherRepository.save(createTeacher());

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
        .when()
                .get(baseUrl + "/" + teacher.getId()).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(teacher.getId().toString()))
                .body("firstName", equalTo(teacher.getFirstName()))
                .body("lastName", equalTo(teacher.getLastName()));
        //@formatter:on
    }

    @Test
    void testPostTeacher() {
        Teacher teacher = createTeacher();

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
                .body(JsonUtil.toJson(teacher)).log().all()
        .when()
                .post(baseUrl).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("firstName", equalTo(teacher.getFirstName()))
                .body("lastName", equalTo(teacher.getLastName()));
        //@formatter:on

        Optional<Teacher> dbTeacher = teacherRepository
                .findTeacherByFirstNameAndLastName(teacher.getFirstName(), teacher.getLastName());
        assertTrue(dbTeacher.isPresent());
    }

    @Test
    void testDeleteTeacher() {
        Teacher teacher = teacherRepository.save(createTeacher());

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
        .when()
                .delete(baseUrl + "/" + teacher.getId()).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on

        Optional<Teacher> dbTeacher = teacherRepository
                .findTeacherByFirstNameAndLastName(teacher.getFirstName(), teacher.getLastName());
        assertTrue(dbTeacher.isEmpty());
    }
}
