package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtException implements ErrorCode {

	UNKNOWN_ERROR(401, "S001", "예상치 못한 오류가 발생했습니다."),
	MAL_FORMED_TOKEN(402, "S002", "잘못된 JWT 서명입니다."),
	EXPIRED_TOKEN(403, "S003", "만료된 토큰입니다."),
	UNSUPPORTED_TOKEN(404, "S004", "지원되지 않는 토큰입니다."),
	ACCESS_DENIED(405, "S005", "접근이 거부되었습니다."),
	ILLEGAL_TOKEN(406, "S006", "JWT 토큰이 잘못되었습니다."),
	ADDITIONAL_REQUIRED_TOKEN(407, "S007", "추가 정보를 입력해야 합니다.");

	private final int status;
	private final String code;
	private final String message;

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

