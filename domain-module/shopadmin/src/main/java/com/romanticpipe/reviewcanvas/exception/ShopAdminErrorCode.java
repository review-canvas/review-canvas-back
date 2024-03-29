package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {
	SHOP_ADMIN_NOT_FOUND(400, "S001", "아이디 혹은 비밀번호가 잘못 입력되었습니다.");

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
