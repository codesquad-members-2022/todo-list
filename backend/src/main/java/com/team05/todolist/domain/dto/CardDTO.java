package com.team05.todolist.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="CardDTO : 카드 정보를 가지고 있는 모델")
@Getter
public class CardDTO implements Comparable<CardDTO> {

	@ApiModelProperty(value="카드 id")
	private Integer cardId;
	@ApiModelProperty(value="카드 등록시 : 해당 섹션의 카드 총 개수")
	private Integer order;
	@ApiModelProperty(value="카드 제목")
	private String title;
	@ApiModelProperty(value="카드 내용")
	private String content;
	@ApiModelProperty(value="현재 또는 이동 후 섹션 [todo /doing /done]")
	private String section;
	@ApiModelProperty(value="사용자 에이전트 정보")
	private String author;

	private CardDTO() {
	}

	public CardDTO(Integer order, String title, String content, String section, String author) {
		this.order = order;
		this.title = title;
		this.content = content;
		this.section = section;
		this.author = author;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	@Override
	public int compareTo(CardDTO o) {
		return this.order - o.order;
	}
}
