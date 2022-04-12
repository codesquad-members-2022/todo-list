package codesquad.be.todoserver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("API /api/todos/* 인수 테스트")
class TodosApiAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 특정_투두리스트_조회_성공() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)

        .when()
            .get("/api/todos/1")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(1))
            .body("title", equalTo("Github 공부하기"))
            .body("contents", equalTo("add, commit, push"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("doing"));
    }

    @Test
    void 특정_투두리스트_조회_실패() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)

        .when()
            .get("/api/todos/4444")

        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
