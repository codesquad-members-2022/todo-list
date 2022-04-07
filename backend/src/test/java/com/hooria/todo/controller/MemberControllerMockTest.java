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
    @DisplayName("")
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
                content().contentType(MediaType.APPLICATION_JSON_VALUE),
                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).add(ArgumentMatchers.refEq(newMember));
    }

    @Test
    @DisplayName("")
    void getAllMembers() throws Exception {

        // given
        List<Member> members = List.of();
        given(memberService.selectAll()).willReturn(members);

        // when
        ResultActions resultActions = mvc.perform(get("/api/users"));

        // then
        resultActions.andExpectAll(
                content().contentType(MediaType.APPLICATION_JSON),
//                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).selectAll();
    }

    @Test
    @DisplayName("")
    void getMember() throws Exception {

        // given
        String userId = "userId";
        Member member = Member.of(userId, "password", "name");
        given(memberService.selectById(userId)).willReturn(member);

        // when
        ResultActions resultActions = mvc.perform(get("/api/users/" + userId));

        // then
        resultActions.andExpectAll(
                content().contentType(MediaType.APPLICATION_JSON),
//                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );

        verify(memberService).selectById(userId);
    }
}
