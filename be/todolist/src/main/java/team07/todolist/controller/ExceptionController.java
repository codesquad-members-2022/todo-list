package team07.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> badRequestException(final IllegalStateException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
