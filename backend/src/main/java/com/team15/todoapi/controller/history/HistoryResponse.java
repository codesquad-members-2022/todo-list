package com.team15.todoapi.controller.history;

import com.team15.todoapi.domain.History;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryResponse {

	private Long id;
	private LocalDateTime created_at;
	private Long card_id;
	private Long member_id;
	private int old_section;
	private int current_section;
	private String code_description;
	private String title;
	private String flag_description;

	public static HistoryResponse from(History history){
		return new HistoryResponse(history.getId(), history.getCreated_at(), history.getCard_id()
		, history.getMember_id(), history.getOld_section(), history.getCurrent_section(), history.getCode_description()
		, history.getTitle(), history.getFlag_description());
	}

}
