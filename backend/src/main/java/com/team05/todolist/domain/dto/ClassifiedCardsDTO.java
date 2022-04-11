package com.team05.todolist.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public class ClassifiedCardsDTO {

	private final Map<String, List<CardDTO>> classifiedCards;

	public ClassifiedCardsDTO() {
		this.classifiedCards = new ConcurrentHashMap<>();
		classifiedCards.put("todo", new ArrayList<>());
		classifiedCards.put("doing", new ArrayList<>());
		classifiedCards.put("done", new ArrayList<>());
	}

	public List<CardDTO> get(String section) {
		return classifiedCards.get(section);
	}
}
