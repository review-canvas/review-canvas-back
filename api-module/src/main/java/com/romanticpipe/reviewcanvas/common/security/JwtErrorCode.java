package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtErrorCode implements ErrorCode {

	UNKNOWN_ERROR(401, "S001", "An unexpected error has occurred."),
	MAL_FORMED_TOKEN(402, "S002", "The JWT signature is incorrect."),
	EXPIRED_TOKEN(403, "S003", "The token has expired."),
	UNSUPPORTED_TOKEN(404, "S004", "The token is unsupported."),
	ACCESS_DENIED(405, "S005", "Access is denied."),
	ILLEGAL_TOKEN(406, "S006", "The JWT token is invalid.");

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

