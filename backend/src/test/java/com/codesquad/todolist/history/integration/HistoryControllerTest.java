package com.codesquad.todolist.history.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.codesquad.todolist.SetUp;
import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.user.User;
import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ComponentScan
@Sql("classpath:/schema.sql")
@Rollback(false)
@DisplayName("HistoryController 통합 테스트")
public class HistoryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SetUp setUp;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("히스토리 요청을 보내면 히스토리와 변경된 필드를 200 OK 로 응답받는다")
    public void findHistoriesTest() {
        // given
        User user = setUp.createUser(new User("유저 이름"));
        Column column_1 = setUp.createColumn(new Column(user.getUserId(), "컬럼 이름"));
        Column column_2 = setUp.createColumn(new Column(user.getUserId(), "이동한 컬럼 이름"));
        Card card = setUp.createCard(new Card(column_1.getColumnId(), "제목", "내용", "작성자", 1));

        // 슬라이스 크기인 5개를 초과한 History 객체 저장
        History history_5 = setUp.createHistory(new History(card.getCardId(), Action.CREATE));

        History history_4 = setUp.createHistory(new History(card.getCardId(), Action.DELETE));
        History history_3 = setUp.createHistory(new History(card.getCardId(), Action.CREATE));
        History history_2 = setUp.createHistory(new History(card.getCardId(), Action.MOVE));
        History history_1 = setUp.createHistory(new History(card.getCardId(), Action.UPDATE));
        History history_0 = setUp.createHistory(new History(card.getCardId(), Action.DELETE));

        setUp.createModifiedFields(List.of(
            new ModifiedField(null, history_2.getHistoryId(), Field.COLUMN,
                column_1.getColumnId().toString(), column_2.getColumnId().toString()),
            new ModifiedField(null, history_1.getHistoryId(), Field.TITLE, "제목", "변경된 제목"),
            new ModifiedField(null, history_1.getHistoryId(), Field.CONTENT, "내용", "변경된 내용"),
            new ModifiedField(null, history_1.getHistoryId(), Field.AUTHOR, "작성자", "변경된 작성자")
        ));

        given()
            .param("page", 1)
            .param("size", 5)
            .contentType("application/json")
            .log().all()
            .when()
            .get("/histories")
            .then()
            .statusCode(200)
            .body("data[0].action", equalTo("DELETE"))
            .body("data[1].action", equalTo("UPDATE"))
            .body("data[2].action", equalTo("MOVE"))
            .body("data[3].action", equalTo("CREATE"))
            .body("data[4].action", equalTo("DELETE"))
            .body("hasNext", equalTo(true))

            .body("data[1].fields[0].field", equalTo("TITLE"))
            .body("data[1].fields[0].oldValue", equalTo("제목"))
            .body("data[1].fields[0].newValue", equalTo("변경된 제목"))

            .body("data[1].fields[1].field", equalTo("CONTENT"))
            .body("data[1].fields[1].oldValue", equalTo("내용"))
            .body("data[1].fields[1].newValue", equalTo("변경된 내용"))

            .body("data[1].fields[2].field", equalTo("AUTHOR"))
            .body("data[1].fields[2].oldValue", equalTo("작성자"))
            .body("data[1].fields[2].newValue", equalTo("변경된 작성자"))

            .body("data[2].fields[0].field", equalTo("COLUMN"))
            .body("data[2].fields[0].oldValue", equalTo("컬럼 이름"))
            .body("data[2].fields[0].newValue", equalTo("이동한 컬럼 이름"));

    }

}
