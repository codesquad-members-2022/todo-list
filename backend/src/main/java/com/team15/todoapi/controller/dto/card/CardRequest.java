package com.team15.todoapi.controller.dto.card;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardRequest {
	
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private String userId;
	private int section;

	//iOS요청 : 각 기능별로 request객체를 통일 요청하여 추가
	private Long id;
	private LocalDateTime createdAt;

}
