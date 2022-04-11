package com.team15.todoapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor
public class Member {

	private Long id;
	private String userId;
	private String name;

	public static Member of(Long id, String userId, String name) {
		return new Member(id, userId, name);
	}
}
