package com.example.backend.domain;

public class Log {
	private Long id;
	private String title;
	private String prevColumnName;
	private String currentColumnName;
	private String actionType;
	private String createdDate;

	public Log(Long id, String title, String prevColumnName, String currentColumnName, String actionType, String createdDate) {
		this.id = id;
		this.title = title;
		this.prevColumnName = prevColumnName;
		this.currentColumnName = currentColumnName;
		this.actionType = actionType;
		this.createdDate = createdDate;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevColumnName() {
		return prevColumnName;
	}

	public String getCurrentColumnName() {
		return currentColumnName;
	}

	public String getActionType() {
		return actionType;
	}

	public static class Builder {
		private Long id;
		private String title;
		private String prevColumnName;
		private String currentColumnName;
		private String actionType;
		private String createdDate;


		public Builder id(Long id) {
			this.id = id;
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

		public Builder currentColumnName(String currentColumnName) {
			this.currentColumnName = currentColumnName;
			return this;
		}

		public Builder actionType(String actionType) {
			this.actionType = actionType;
			return this;
		}

		public Builder createdDate(String createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Log build() {
			return new Log(id, title, prevColumnName, currentColumnName, actionType, createdDate);
		}
	}
}
