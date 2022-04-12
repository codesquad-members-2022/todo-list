package com.codesquad.todolist.history.unit;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codesquad.todolist.history.HistoryController;
import com.codesquad.todolist.history.HistoryService;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.dto.HistoryResponse;
import com.codesquad.todolist.history.dto.ModifiedFieldResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@WebMvcTest(HistoryController.class)
@DisplayName("HistoryController 단위 테스트")
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Test
    @DisplayName("모든 히스토리 정보를 요청하고 반환 받는다")
    public void findAll() throws Exception {
        // given
        List<HistoryResponse> historyResponses = List.of(
            new HistoryResponse(1, "유저 이름", "컬럼 이름", "변경된 제목", Action.CREATE, null, null),
            new HistoryResponse(2, "유저 이름", "변경된 컬럼 이름", "변경된 제목", Action.MOVE, null,
                List.of(
                    new ModifiedFieldResponse(1, Field.COLUMN, "컬럼 이름", "변경된 컬럼 이름")
                )
            ),
            new HistoryResponse(3, "유저 이름", "변경된 컬럼 이름", "변경된 제목", Action.UPDATE, null,
                List.of(
                    new ModifiedFieldResponse(2, Field.TITLE, "제목", "변경된 제목"),
                    new ModifiedFieldResponse(3, Field.CONTENT, "내용", "변경된 제목"),
                    new ModifiedFieldResponse(4, Field.AUTHOR, "작성자", "변경된 작성자")
                )
            ),
            new HistoryResponse(4, "유저 이름", "변경된 컬럼 이름", "변경된 제목", Action.DELETE, null, null)
        );
        given(historyService.findAll())
            .willReturn(historyResponses);

        // when
        ResultActions actions = mockMvc.perform(get("/histories")
            .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].historyId").value(1))
            .andExpect(jsonPath("$[1].historyId").value(2))
            .andExpect(jsonPath("$[1].fields[0].modifiedFieldId").value(1))
            .andExpect(jsonPath("$[2].historyId").value(3))
            .andExpect(jsonPath("$[2].fields[0].modifiedFieldId").value(2))
            .andExpect(jsonPath("$[2].fields[1].modifiedFieldId").value(3))
            .andExpect(jsonPath("$[2].fields[2].modifiedFieldId").value(4))
            .andExpect(jsonPath("$[3].historyId").value(4));
    }

}
