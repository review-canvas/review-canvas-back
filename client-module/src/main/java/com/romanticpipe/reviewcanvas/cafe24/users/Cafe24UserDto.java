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
public record Cafe24UserDto(
	String memberId,
	String mallId,
	String name,
	String nickName,
	String gender,
	String nationality,
	LocalDate birthday
) {

	public boolean isFullContent() {
		return StringUtils.hasText(memberId) && StringUtils.hasText(mallId) && StringUtils.hasText(name)
			&& StringUtils.hasText(nickName) && StringUtils.hasText(gender) && StringUtils.hasText(nationality)
			&& birthday != null;
	}

	public void validateContent() {
		if (!isFullContent()) {
			throw new BusinessException(Cafe24ErrorCode.USER_INFO_NOT_ENOUGH);
		}
	}

	public User toUserEntity() {
		return User.builder()
			.memberId(memberId)
			.mallId(mallId)
			.name(name)
			.nickName(nickName)
			.gender(Gender.valueOf(gender))
			.nationality(nationality)
			.birth(birthday)
			.build();
	}
}
