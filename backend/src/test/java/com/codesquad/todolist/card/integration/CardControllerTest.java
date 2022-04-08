package com.codesquad.todolist.card.integration;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void updateCardTest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "제목");
        requestBody.put("content", "내용");
        requestBody.put("author", "작성자");

        given()
            .contentType("application/json")
            .pathParam("id", 1)
            .body(requestBody)
            .log().all()
        .when()
            .put("cards/{id}")
        .then()
            .statusCode(200);
    }
}
