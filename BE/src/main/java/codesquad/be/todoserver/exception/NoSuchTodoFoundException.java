package codesquad.be.todoserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchTodoFoundException extends RuntimeException {

	public NoSuchTodoFoundException(String message) {
		super(message);
	}
}
