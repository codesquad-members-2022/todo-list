package com.example.backend.domain;

public class Log {
	private Long id;
	private String author;
	private String title;
	private String prevColumnName;
	private String curColumnName;
	private ActionType actionType;
	private String createdDate;

	public Log(Long id, String author, String title, String prevColumnName, String curColumnName,
		ActionType actionType, String createdDate) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.prevColumnName = prevColumnName;
		this.curColumnName = curColumnName;
		this.actionType = actionType;
		this.createdDate = createdDate;
	}

	public String createText() {

		StringBuilder sb = new StringBuilder();
		switch (actionType.getName()) {
			case ("add"):
				sb.append(curColumnName)
					.append("에 ")
					.append(title)
					.append("을(를) 등록하였습니다");
				return sb.toString();

			case ("remove"):
				sb.append(curColumnName)
					.append("의 ")
					.append(title)
					.append("이(가) 삭제되었습니다.");
				return sb.toString();

			case ("update"):
				sb.append(title)
					.append("이(가) 수정되었습니다.");
				return sb.toString();

			case ("move"):
				sb.append(title)
					.append("을(를) ")
					.append(prevColumnName)
					.append("에서 ")
					.append(curColumnName)
					.append("(으)로 이동하였습니다.");
				return sb.toString();
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevColumnName() {
		return prevColumnName;
	}

	public String getCurColumnName() {
		return curColumnName;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public static class Builder {
		private Long id;
		private String author;
		private String title;
		private String prevColumnName;
		private String curColumnName;
		private ActionType actionType;
		private String createdDate;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder prevColumnName(String prevColumnName) {
			this.prevColumnName = prevColumnName;
			return this;
		}

		public Builder curColumnName(String curColumnName) {
			this.curColumnName = curColumnName;
			return this;
		}

		public Builder actionType(ActionType actionType) {
			this.actionType = actionType;
			return this;
		}

		public Builder createdDate(String createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Log build() {
			return new Log(id, author, title, prevColumnName, curColumnName, actionType, createdDate);
		}
	}

}
