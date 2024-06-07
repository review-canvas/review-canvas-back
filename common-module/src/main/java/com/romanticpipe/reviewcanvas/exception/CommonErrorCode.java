package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

	INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다."),
	INTERNAL_SERVER_ERROR(500, "C002", "서버 오류"),
	OUTER_CLIENT_REQUEST_ERROR(400, "C003", "외부 api 호출에 실패했습니다."),
	FILE_UPLOAD_FAILED(400, "C004", "파일 업로드에 실패했습니다."),
	UNAUTHORIZED(403, "C005", "데이터 접근 권한이 없습니다."),
	INCOMPATIBLE_FORMAT_TYPE(400, "C006", "지원하지 않는 파일 형식입니다.");


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
