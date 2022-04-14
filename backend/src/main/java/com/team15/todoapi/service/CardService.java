package com.team15.todoapi.service;

import com.team15.todoapi.controller.dto.DefaultResponse;
import com.team15.todoapi.controller.dto.card.CardRequest;
import com.team15.todoapi.controller.dto.card.CardResponse;
import com.team15.todoapi.domain.Card;
import com.team15.todoapi.domain.CardAction;
import com.team15.todoapi.domain.Member;
import com.team15.todoapi.repository.CardRepository;
import com.team15.todoapi.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;
	private final MemberRepository memberRepository;
	private final HistoryService historyService;

	public List<CardResponse.ListInfo> findAll(String userId) {
		Member member = selectMemberInfo(userId);

		List<Card> cards = cardRepository.findAll(member.getId());

		return cards.stream().map(CardResponse.ListInfo::from).collect(Collectors.toList());
	}

	@Transactional
	public DefaultResponse add(CardRequest cardRequest) {
		Member member = selectMemberInfo(cardRequest.getUserId());

		DefaultResponse defaultResponse = new DefaultResponse();
		Card card = Card.of(cardRequest, member.getId());
		card = cardRepository.add(card);

		try {
			card.insertAction(card, CardAction.ADD.getCode());
			historyService.add(card);
			defaultResponse.setHttpStatus(HttpStatus.CREATED);
		} catch (Exception e) {
			defaultResponse.setHttpStatus(HttpStatus.MULTI_STATUS);
			log.info("CARD ADD - CardService.add Exception = {}" + e.getMessage());
		}finally{
			defaultResponse.setCustomResponse(CardResponse.AddInfo.from(card));
		}

		return defaultResponse;
	}

	private Member selectMemberInfo(String userId) {
		Member member = memberRepository.findByUserId(userId);
		return member;
	}
}
