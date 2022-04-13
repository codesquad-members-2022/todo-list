package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.controller.model.RegisterTodoDto;
import codesquad.be.todoserver.domain.Todo;

public class TodoDtoMapper {

	private TodoDtoMapper() {
	}

	public static Todo toDomainFromRegisterTodoDto(RegisterTodoDto registerTodoDto) {
		return new Todo(registerTodoDto.getTitle(),
			registerTodoDto.getContents(),
			registerTodoDto.getUser(),
			registerTodoDto.getStatus());
	}

}
