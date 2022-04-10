package com.team05.todolist.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="CardDTO : 카드 정보를 가지고 있는 모델")
@Getter
public class CardDTO {

	@ApiModelProperty(hidden = true)
	private Integer cardId;
	@ApiModelProperty(value="카드 인덱스", required = true)
	private Integer order;
	@ApiModelProperty(value="카드 제목", required = true)
	private String title;
	@ApiModelProperty(value="카드 내용", required = true)
	private String content;
	@ApiModelProperty(value="카드 섹션 정보 [todo /doing /done]", required = true)
	private String section;

	public CardDTO(Integer order, String title, String content, String section) {
		this.order = order;
		this.title = title;
		this.content = content;
		this.section = section;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
}
