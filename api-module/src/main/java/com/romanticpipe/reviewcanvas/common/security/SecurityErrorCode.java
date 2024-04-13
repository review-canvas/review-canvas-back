package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SecurityErrorCode implements ErrorCode {
	UNAUTHORIZED(401, "S001", "Unauthorized Admin error."),
	NON_BEARER(401, "S002", "Bearer format is required in the Authorization header."),
	UNKNOWN_ERROR(403, "S003", "An unexpected error has occurred."),
	MAL_FORMED_TOKEN(403, "S004", "The JWT signature is incorrect."),
	EXPIRED_TOKEN(403, "S005", "The token has expired."),
	UNSUPPORTED_TOKEN(403, "S006", "The token is unsupported."),
	ACCESS_DENIED(403, "S007", "Access is denied."),
	ILLEGAL_TOKEN(403, "S008", "The JWT token is invalid.");

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

