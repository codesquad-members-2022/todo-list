package com.team15.todoapi.service;

import com.team15.todoapi.controller.card.CardRequest;
import com.team15.todoapi.controller.card.CardResponse;
import com.team15.todoapi.domain.Card;
import com.team15.todoapi.domain.Member;
import com.team15.todoapi.repository.CardRepository;
import com.team15.todoapi.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;
	private final MemberRepository memberRepository;

	public List<CardResponse.ListInfo> findAll(String userId) {
		Member member = selectMemberInfo(userId);

		List<Card> cards = cardRepository.findAll(member.getId());

		return cards.stream().map(CardResponse.ListInfo::from).collect(Collectors.toList());
	}

	public CardResponse.AddInfo add(CardRequest cardRequest) {
		Member member = selectMemberInfo(cardRequest.getUserId());

		Card card = Card.of(cardRequest, member.getId());
		card = cardRepository.add(card);

		return CardResponse.AddInfo.from(card);
	}

	private Member selectMemberInfo(String userId) {
		Member member = memberRepository.findByUserId(userId);
		return member;
	}
}
