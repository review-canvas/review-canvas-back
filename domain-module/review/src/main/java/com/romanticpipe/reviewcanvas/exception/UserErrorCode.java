package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	USER_NOT_FOUND(400, "U001", "사용자를 찾을 수 없습니다.");

	private final int status;
	private final String code;
	private final String message;

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public String getMessage() {
		return null;
	}
}
