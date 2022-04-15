package codesquad.be.todoserver.domain;

import java.util.function.Function;

public enum Action {

	ADD(todo -> new History(todo.getId(), todo.getTitle(), todo.getUser(), "add",
		"", todo.getStatus())),
	REMOVE(todo -> new History(todo.getId(), todo.getTitle(), todo.getUser(), "remove",
		"", todo.getStatus()));


	private final Function<Todo, History> fuc;

	Action(Function<Todo, History> fuc) {
		this.fuc = fuc;
	}

	public History createHistoryBy(Todo todo) {
		return fuc.apply(todo);
	}

}
