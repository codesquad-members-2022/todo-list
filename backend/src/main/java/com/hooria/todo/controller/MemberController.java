package com.hooria.todo.controller;

import com.hooria.todo.domain.Member;
import com.hooria.todo.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Member(사용자) Controller")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Member addMember(@RequestBody Member member, HttpServletResponse response) {
        return memberService.add(member);
    }

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
        return memberService.selectAll();
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
        return memberService.selectById(userId);
    }
}
