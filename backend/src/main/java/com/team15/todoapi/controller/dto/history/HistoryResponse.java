package com.team15.todoapi.controller.dto.history;

import com.team15.todoapi.domain.History;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryResponse {

	private Long id;
	private LocalDateTime createdAt;
	private Long cardId;
//	private Long memberId;
	private String oldSection;
	private String currentSection;
	private String action;
	private String title;

	public static HistoryResponse from(History history){
		return new HistoryResponse(history.getId(), history.getCreatedAt(), history.getCardId()
		, history.getOldSection(), history.getCurrentSection(), history.getAction()
		, history.getTitle());
	}

}
