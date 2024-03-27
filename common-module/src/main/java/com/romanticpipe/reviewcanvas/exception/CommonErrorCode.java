package com.romanticpipe.reviewcanvas.exception;

public enum CommonErrorCode implements ErrorCode {

	INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다."),
	INTERNAL_SERVER_ERROR(500, "C004", "서버 오류");


	private final int status;
	private final String code;
	private final String message;

	CommonErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

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
