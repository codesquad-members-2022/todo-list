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
		return actionType.getText(title, prevColumnName, curColumnName);
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
