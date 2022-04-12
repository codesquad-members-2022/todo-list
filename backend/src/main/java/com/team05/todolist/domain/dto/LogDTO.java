package com.team05.todolist.domain.dto;

import com.team05.todolist.domain.Event;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;

@ApiModel(value = "LogDTO: 카드 CRUD 로그")
public class LogDTO {

	@ApiModelProperty(value = "로그 id")
	private Integer logId;
	@ApiModelProperty(value = "카드 CRUD 분류")
	private Event logEvent;
	@ApiModelProperty(value = "로그 생성 시간")
	private LocalDateTime logTime;
	@ApiModelProperty(value = "카드 제목")
	private String title;
	@ApiModelProperty(value = "카드 이동 전 section")
	private String prevSection;
	@ApiModelProperty(value = "카드 이동 후 section")
	private String section;

	private LogDTO() {
	}

	public LogDTO(Integer logId, String logEvent, LocalDateTime logTime, String title, String section) {
		this(logId, logEvent, logTime, title, null, section);
	}

	public LogDTO(Integer logId, String logEvent, LocalDateTime logTime, String title,
		String prevSection, String section) {
		this.logId = logId;
		this.logEvent = Event.getEvent(logEvent);
		this.logTime = checkDateTimeNull(logTime);
		this.title = title;
		this.prevSection = prevSection;
		this.section = section;
	}

	private LocalDateTime checkDateTimeNull(LocalDateTime logTime) {
		if (logTime == null) {
			return LocalDateTime.now();
		}
		return logTime;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevSection() {
		return prevSection;
	}

	public String getSection() {
		return section;
	}

	public String getLogEventType() {
		return logEvent.getEventType();
	}

	public LocalDateTime getLogTime() {
		return logTime;
	}


}
