package com.romanticpipe.reviewcanvas.cafe24.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.romanticpipe.reviewcanvas.cafe24.Cafe24ErrorCode;
import com.romanticpipe.reviewcanvas.domain.Gender;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24User(
	String memberId,
	String name,
	String nickName,
	String gender,
	String nationality,
	LocalDate birthday
) {

	public boolean isContentExist() {
		return StringUtils.hasText(memberId) && StringUtils.hasText(name);
	}

	public void validateContent() {
		if (!isContentExist()) {
			throw new BusinessException(Cafe24ErrorCode.USER_INFO_NOT_ENOUGH);
		}
	}

	public User toUserEntity(String mallId) {
		return User.builder()
			.memberId(memberId)
			.mallId(mallId)
			.name(name)
			.nickName(nickName)
			.gender(StringUtils.hasText(gender) ? Gender.valueOf(gender) : null)
			.nationality(nationality)
			.birth(birthday)
			.build();
	}
}
