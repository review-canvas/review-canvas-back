package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SecurityErrorCode implements ErrorCode {

	UNAUTHORIZED(401, "S001", "unauthorized token error."),
	FORBIDDEN(403, "S003", "forbidden token error, plz recheck token");

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
