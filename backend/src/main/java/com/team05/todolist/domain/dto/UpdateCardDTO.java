package com.team05.todolist.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="UpdateCardDTO : 카드 이동시 요청데이터를 가지고 있는 모델")
@Getter
public class UpdateCardDTO {

    @ApiModelProperty(value="카드 등록시 : 해당 섹션의 카드 총 개수", required = true)
    private Integer order;
    @ApiModelProperty(value="카드 제목", required = true)
    private String title;
    @ApiModelProperty(value="카드 내용", required = true)
    private String content;
    @ApiModelProperty(value="현재 또는 이동 후 섹션 [todo /doing /done]", required = true)
    private String section;

}
