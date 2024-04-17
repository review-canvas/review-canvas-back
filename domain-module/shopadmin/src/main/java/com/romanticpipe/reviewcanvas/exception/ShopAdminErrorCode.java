package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {
	REVIEW_THEME_NOT_FOUND(400, "R001", "테마를 찾을 수 없습니다."),
	NOT_GENERAL_REVIEW_THEME(400, "R002", "기본 테마가 아닙니다."),
	DENY_ACCESS_REVIEW_STATUS(400, "R003", "현재 승인 대기 중인 리뷰에 접근할 수 있는 권한이 없습니다.");

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
