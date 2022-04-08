package com.hooria.todo.service;

import com.hooria.todo.domain.Member;
import com.hooria.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member add(Member member) {
        return memberRepository.insert(member);
    }

    public List<Member> selectAll() {
        return memberRepository.findAll();
    }

    public Member selectById(String userId) {
        return memberRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
    }
}
