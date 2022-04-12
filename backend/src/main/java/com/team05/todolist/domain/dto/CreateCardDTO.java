package com.team05.todolist.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "CreateCardDTO : 카드 등록시 Request Body에 담길 모델")
@Getter
public class CreateCardDTO {

	@ApiModelProperty(hidden = true)
	private Integer cardId;
	@ApiModelProperty(value = "카드 등록시 : 해당 섹션의 카드 총 개수", required = true)
	private Integer number;
	@ApiModelProperty(value = "카드 제목", required = true)
	private String title;
	@ApiModelProperty(value = "카드 내용", required = true)
	private String content;
	@ApiModelProperty(value = "현재 또는 이동 후 섹션 [todo /doing /done]", required = true)
	private String section;

	private CreateCardDTO() {
	}

	public CreateCardDTO(Integer number, String title, String content, String section) {
		this.number = number;
		this.title = title;
		this.content = content;
		this.section = section;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
}
