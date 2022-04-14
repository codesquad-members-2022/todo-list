package com.team15.todoapi.service;

import com.team15.todoapi.controller.history.HistoryResponse;
import com.team15.todoapi.domain.Card;
import com.team15.todoapi.domain.History;
import com.team15.todoapi.domain.Member;
import com.team15.todoapi.repository.HistoryRepository;
import com.team15.todoapi.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {

	private final HistoryRepository historyRepository;
	private final MemberRepository memberRepository;

	public List<HistoryResponse> findAll(String userId) {
		Member member = selectMemberInfo(userId);

		List<History> histories = historyRepository.findAll(member.getId());
		return histories.stream().map(HistoryResponse::from).collect(Collectors.toList());
	}

	public int add(Card card) {
		return historyRepository.insert(card);
	}

	private Member selectMemberInfo(String userId) {
		Member member = memberRepository.findByUserId(userId);
		return member;
	}
}
