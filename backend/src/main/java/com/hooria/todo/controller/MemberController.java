package com.hooria.todo.controller;

import com.hooria.todo.domain.Member;
import com.hooria.todo.repository.MemberRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@Api(tags = "Member(사용자) Controller")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @ApiOperation(
            value = "모든 사용자 목록 조회",
            notes = "모든 사용자 목록을 조회한다.",
            produces = "application/json",
            response = List.class
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "조회 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @ApiOperation(
            value = "userId 에 해당하는 사용자 조회",
            notes = "userId 에 해당하는 사용자를 조회한다.",
            produces = "application/json",
            response = Member.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userId",
                    value = "사용자 아이디"
            )
    })
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "조회 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @GetMapping("/{userId}")
    public Member getMember(@PathVariable String userId) {
        return memberRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
}
