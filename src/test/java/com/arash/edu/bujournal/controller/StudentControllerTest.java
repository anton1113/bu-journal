package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.BaseIntegrationTest;
import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.repository.StudentRepository;
import com.arash.edu.bujournal.util.JsonUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.arash.edu.bujournal.util.TestDataProvider.createStudent;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentControllerTest extends BaseIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Value("http://localhost:${local.server.port}/v1/students")
    private String baseUrl;

    @BeforeEach
    void beforeEach() {
        studentRepository.deleteAll();
    }

    @Test
    void testGetStudent() {
        Student student = studentRepository.save(createStudent());

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
        .when()
                .get(baseUrl + "/" + student.getId()).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(student.getId().toString()))
                .body("firstName", equalTo(student.getFirstName()))
                .body("lastName", equalTo(student.getLastName()));
        //@formatter:on
    }

    @Test
    void testPostStudent() {
        Student student = createStudent();

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
                .body(JsonUtil.toJson(student)).log().all()
        .when()
                .post(baseUrl).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("firstName", equalTo(student.getFirstName()))
                .body("lastName", equalTo(student.getLastName()));
        //@formatter:on

        Optional<Student> dbStudent = studentRepository
                .findStudentByFirstNameAndLastName(student.getFirstName(), student.getLastName());
        assertTrue(dbStudent.isPresent());
    }

    @Test
    void testDeleteStudent() {
        Student student = studentRepository.save(createStudent());

        //@formatter:off
        given()
                .headers(HttpHeaders.CONTENT_TYPE, ContentType.JSON.toString())
        .when()
                .delete(baseUrl + "/" + student.getId()).prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value());
        //@formatter:on

        Optional<Student> dbStudent = studentRepository
                .findStudentByFirstNameAndLastName(student.getFirstName(), student.getLastName());
        assertTrue(dbStudent.isEmpty());
    }
}
