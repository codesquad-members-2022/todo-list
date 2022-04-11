package team07.todolist.service;

public enum Type {

	ADD(1),
	REMOVE(2),
	UPDATE(3),
	MOVE(4);

	private final int typeValue;

	Type(int typeValue) {
		this.typeValue = typeValue;
	}

	public int getTypeValue() {
		return typeValue;
	}
}
