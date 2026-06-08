package com.springAi.springAI.tools;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateTimeTool {

	@Tool(description = "Get the current date and time")
	public String getCurrentDateTime() {
		java.time.ZonedDateTime now = LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId());
		return now.toString() + " (Day of week: " + now.getDayOfWeek() + ")";
	}
}
