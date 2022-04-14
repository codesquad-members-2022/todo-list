package com.team15.todoapi.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class DefaultResponse {

	private HttpStatus httpStatus;
	private Object customResponse;

}
