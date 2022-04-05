package com.hooria.todo.controller;

import com.hooria.todo.domain.Member;
import com.hooria.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Member getMember(@PathVariable String userId){
        return memberRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
}
