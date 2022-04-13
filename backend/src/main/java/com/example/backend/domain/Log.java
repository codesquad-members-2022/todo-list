package com.example.backend.domain;

import com.example.backend.web.dto.LogSaveRequestDto;

public class Log {
	private Long id;
	private String author;
	private String text;
	private String createdDate;

	public static Log from(LogSaveRequestDto dto) {
		Log log = new Log();
		log.author = "sam";
		log.text = log.createText(dto);
		return log;
	}

	private String createText(LogSaveRequestDto dto) {
		String title = dto.getTitle();
		String prevColumnName = dto.getPrevColumnName();
		String currentColumnName = dto.getCurrentColumnName();
		ActionType actionType = dto.getActionType();

		StringBuilder sb = new StringBuilder();
		switch (actionType.getName()) {
			case ("add"):
				sb.append(currentColumnName)
					.append("에 ")
					.append(title)
					.append("을(를) 등록하였습니다");
				return sb.toString();

			case ("remove"):
				sb.append(currentColumnName)
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
					.append(currentColumnName)
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

	public String getText() {
		return text;
	}

	public String getCreatedDate() {
		return createdDate;
	}
}
