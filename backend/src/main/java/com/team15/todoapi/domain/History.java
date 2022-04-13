package com.team15.todoapi.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class History {

	private Long id;
	private LocalDateTime created_at;
	private Long card_id;
	private Long member_id;
	private int old_section;
	private int current_section;
	private String code_description;
	private String title;
	private String flag_description;

	public static History of(long id, LocalDateTime created_at, long card_id, long member_id, int old_section, int current_section, String code_description, String title,
		String flag_description) {
		return new History(id, created_at, card_id, member_id, old_section, current_section, code_description, title, flag_description);
	}
}
