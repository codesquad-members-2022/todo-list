package codesquad.be.todoserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codesquad.be.todoserver.service.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("API /api/histories 인수 테스트")
public class HistoriesApiAcceptanceTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	HistoryService historyService;

	@BeforeEach
	@Sql({"/testDB/schema.sql", "/testDB/data.sql"})
	void setUp() {

	}

	@Test
	void 전체_히스토리_조회_성공() throws Exception {
		ResultActions perform = mockMvc.perform(get("/api/histories"));

		perform
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].id").value(1))
			.andExpect(jsonPath("$.[0].todoId").value(1))
			.andExpect(jsonPath("$.[0].todoTitle").value("Github 공부하기"))
			.andExpect(jsonPath("$.[0].action").value("add"))

			.andExpect(jsonPath("$.[1].id").value(2))
			.andExpect(jsonPath("$.[1].todoId").value(1))
			.andExpect(jsonPath("$.[1].todoTitle").value("Github 공부하기"))
			.andExpect(jsonPath("$.[1].action").value("move"))
			.andExpect(jsonPath("$.[1].fromStatus").value("todo"))
			.andExpect(jsonPath("$.[1].toStatus").value("doing"));
	}
}
