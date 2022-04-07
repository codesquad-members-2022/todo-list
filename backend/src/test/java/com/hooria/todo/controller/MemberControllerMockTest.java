package com.hooria.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hooria.todo.domain.Member;
import com.hooria.todo.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("인자로 주어진 신규 가입자를 저장소에 저장한다.")
    void addMember() throws Exception {

        // given
        Member newMember = Member.of("userId", "password", "name");
        given(memberService.add(ArgumentMatchers.refEq(newMember))).willReturn(newMember);

        // when
        ResultActions resultActions = mvc.perform(post("/api/users")
                .content(objectMapper.writeValueAsString(newMember))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        resultActions.andExpectAll(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE),
                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).add(ArgumentMatchers.refEq(newMember));
    }

    @Test
    @DisplayName("저장소에 저장되어 있는 전체 사용자 목록을 읽어 반환한다.")
    void getAllMembers() throws Exception {

        // given
        List<Member> members = List.of();
        given(memberService.selectAll()).willReturn(members);

        // when
        ResultActions resultActions = mvc.perform(get("/api/users"));

        // then
        resultActions.andExpectAll(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).selectAll();
    }

    @Test
    @DisplayName("인자로 주어진 'userId'를 가진 사용자를 저장소에서 찾아 반환한다.")
    void getMember() throws Exception {

        // given
        String userId = "userId";
        Member member = Member.of(userId, "password", "name");
        given(memberService.selectById(userId)).willReturn(member);

        // when
        ResultActions resultActions = mvc.perform(get("/api/users/" + userId));

        // then
        resultActions.andExpectAll(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).selectById(userId);
    }
}
