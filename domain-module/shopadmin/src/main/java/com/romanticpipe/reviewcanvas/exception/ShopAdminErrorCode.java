package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {
	// ReviewDesign
	REVIEW_THEME_NOT_FOUND(400, "R001", "테마를 찾을 수 없습니다."),
	NOT_GENERAL_REVIEW_THEME(400, "R002", "기본 테마가 아닙니다."),

	// Admin
	ADMIN_NOT_FOUND(400, "A001", "Admin을 찾을 수 없습니다."),
	ADMIN_WRONG_PASSWORD(400, "A002", "비밀번호가 잘못 입력되었습니다."),

	// AdminAuth
	ADMIN_AUTH_NOT_FOUND(400, "AA001", "refresh token을 찾을 수 없습니다."),

	// ShopAuthToken
	SHOP_AUTH_TOKEN_NOT_FOUND(400, "SAT001", "ShopAuthToken을 찾을 수 없습니다.");

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
