package com.team05.todolist.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="CardDTO : 카드 정보를 가지고 있는 모델")
@Getter
public class MoveCardDTO {

	@ApiModelProperty(value="이동할 자리 이전 카드의 Order (없으면 -1)", required = true)
	private Integer preOrder;
	@ApiModelProperty(value="이동할 자리 이전 카드의 Order (없으면 -1)", required = true)
	private Integer nextOrder;
	@ApiModelProperty(value="이동하기 전 섹션 [todo /doing /done]", required = true)
	private String prevSection;
	@ApiModelProperty(value="현재 또는 이동 후 섹션 [todo /doing /done]", required = true)
	private String section;

	private MoveCardDTO() {
	}

	public MoveCardDTO(Integer preOrder, Integer nextOrder, String prevSection, String section) {
		this.preOrder = preOrder;
		this.nextOrder = nextOrder;
		this.prevSection = prevSection;
		this.section = section;
	}
}
