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

/**
 * 인수 테스트 : 클라이언트가 Todo를 특정 id로 조회할 수 있게 해주세요.
 * 성공
 * • GET /api/todos/{id} 요청 값을 받는다.
 * • DB 에서 요청 받은 id 로 해당 todo 를 찾아 보낸다
 * • 응답은 반드시 todo 형태로 간다. (200 OK)
 *
 * 실패
 * • GET /api/todos/{id} 요청 값을 받는다.
 * • DB 에서 요청 받은 id 로 해당 todo 를 찾아 보낸다
 * • todo가 없는 경우 에러를 응답한다. (404 NOT FOUND)
 */
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
            .body("status", equalTo("todo"));
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
