package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {

	ADMIN_NOT_FOUND(400, "SA001", "등록되지 않은 아이디입니다."),
	ADMIN_WRONG_PASSWARD(400, "SA002", "비밀번호가 잘못 입력되었습니다."),

	REVIEW_THEME_NOT_FOUND(400, "R001", "테마를 찾을 수 없습니다."),
	NOT_GENERAL_REVIEW_THEME(400, "R002", "기본 테마가 아닙니다."),
	SHOP_ADMIN_NOT_FOUND(400, "SH001", "Shop Admin을 찾을 수 없습니다.");

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
