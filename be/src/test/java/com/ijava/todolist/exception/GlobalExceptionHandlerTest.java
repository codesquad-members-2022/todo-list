package com.ijava.todolist.exception;

import com.ijava.todolist.card.exception.CardRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StubController.class)
class GlobalExceptionHandlerTest {

    private static final String ERROR_REQUEST_URL = "/test";

    @Autowired
    private MockMvc mvc;

    @Nested
    @DisplayName("CardRuntimeException 타입의 예외가 발생하면")
    class CardRuntimeExceptionTest {
        @Test
        void ErrorResponse_를_json_형태로_반환한다() throws Exception {
            // given
            String expectedJson = "{\"statusCode\":400,\"statusName\":\"BAD_REQUEST\",\"message\":\"테스트 예외가 발생하였습니다.\"}";

            // when
            ResultActions result = mvc.perform(get(ERROR_REQUEST_URL));

            // then
            result.andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
        }
    }
}

@RestController
class StubController {

    @GetMapping("/test")
    public String hello() {
        throw new CardRuntimeException("테스트 예외가 발생하였습니다.") {
            @Override
            public HttpStatus getStatus() {
                return HttpStatus.BAD_REQUEST;
            }
        };
    }
}