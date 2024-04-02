package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DesignItemErrorCode implements ErrorCode {
	THEME_NOT_FOUND(400, "D001", "테마를 찾을 수 없습니다.");

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
