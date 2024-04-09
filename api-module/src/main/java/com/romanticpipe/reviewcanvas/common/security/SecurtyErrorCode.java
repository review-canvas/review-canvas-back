package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurtyErrorCode implements ErrorCode {
	UNAUTHORIZED(401, "S001", "Unauthorized Admin error."),
	UNKNOWN_ERROR(403, "S002", "An unexpected error has occurred."),
	MAL_FORMED_TOKEN(403, "S003", "The JWT signature is incorrect."),
	EXPIRED_TOKEN(403, "S004", "The token has expired."),
	UNSUPPORTED_TOKEN(403, "S005", "The token is unsupported."),
	ACCESS_DENIED(403, "S006", "Access is denied."),
	ILLEGAL_TOKEN(403, "S007", "The JWT token is invalid.");

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

