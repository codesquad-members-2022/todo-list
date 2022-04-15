package com.codesquad.todolist.card.integration;

import static io.restassured.RestAssured.given;

import com.codesquad.todolist.SetUp;
import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.user.User;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ComponentScan
public class CardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SetUp setUp;

    private User user;
    private Column column;
    private Card card;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        user = setUp.createUser(new User("유저 이름"));
        column = setUp.createColumn(new Column(user.getUserId(), "컬럼 이름"));
        card = setUp.createCard(new Card(column.getColumnId(), "제목", "내용", "작성자", null));
    }

    @Test
    @DisplayName("카드 생성 요청을 보내면 201 CREATED 를 응답받는다")
    public void createCardTest() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("columnId", column.getColumnId());
        requestBody.put("title", "제목");
        requestBody.put("content", "내용");
        requestBody.put("author", "작성자");
        requestBody.put("nextId", card.getCardId());

        given()
            .contentType("application/json")
            .body(requestBody)
            .log().all()
            .when()
            .post("cards/")
            .then()
            .statusCode(201);
    }

    @Test
    @DisplayName("카드 업데이트 요청을 보내면 200 OK 를 응답받는다")
    public void updateCardTest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "변경된 제목");
        requestBody.put("content", "변경된 내용");
        requestBody.put("author", "변경된 작성자");

        given()
            .contentType("application/json")
            .pathParam("id", card.getCardId())
            .body(requestBody)
            .log().all()
            .when()
            .patch("cards/{id}")
        .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("카드 삭제 요청을 보내면 204 NO_CONTENT 를 응답 받는다")
    public void deleteCardTest() {

        given()
            .pathParam("id", card.getCardId())
            .log().all()
            .when()
            .delete("cards/{id}")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("카드 이동 요청을 보내면 200 OK 를 응답 받는다")
    public void moveCardTest() {
        Column column_2 = setUp.createColumn(new Column(user.getUserId(), "컬럼 이름2"));
        Card card_2 = setUp.createCard(new Card(column_2.getColumnId(), "제목", "내용", "작성자", null));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("columnId", column.getColumnId());
        requestBody.put("nextId", null);

        given()
            .contentType("application/json")
            .pathParam("id", card_2.getCardId())
            .body(requestBody)
            .log().all()
            .when()
            .put("cards/{id}/move")
            .then()
            .statusCode(200);
    }

}
