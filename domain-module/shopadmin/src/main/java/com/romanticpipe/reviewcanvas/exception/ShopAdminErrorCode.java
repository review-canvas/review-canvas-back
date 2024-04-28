package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {
	// ReviewDesign
	REVIEW_THEME_NOT_FOUND(400, "A001", "테마를 찾을 수 없습니다."),
	NOT_GENERAL_REVIEW_THEME(400, "A002", "기본 테마가 아닙니다."),

	// Admin
	ADMIN_NOT_FOUND(400, "A003", "Admin을 찾을 수 없습니다."),
	ADMIN_WRONG_PASSWORD(400, "A004", "비밀번호가 잘못 입력되었습니다."),

	// AdminAuth
	ADMIN_AUTH_NOT_FOUND(400, "A005", "refresh token을 찾을 수 없습니다."),

	// MyReviewDesign
	NOT_REVIEW_DESIGN_OWNER(400, "A006", "리뷰 디자인을 만든 주인이 아닙니다."),

	// ShopAuthToken
	SHOP_AUTH_TOKEN_NOT_FOUND(400, "A007", "ShopAuthToken을 찾을 수 없습니다."),
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
