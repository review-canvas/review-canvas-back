package com.romanticpipe.reviewcanvas.cafe24.users;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24User(
	String memberId,
	String mallId,
	String name,
	String nickName,
	String gender,
	String nationality,
	Date birthday
) {

	public boolean isFullContent() {
		return StringUtils.hasText(memberId) && StringUtils.hasText(mallId) && StringUtils.hasText(name)
			&& StringUtils.hasText(nickName) && StringUtils.hasText(gender) && StringUtils.hasText(nationality)
			&& birthday != null;
	}
}
