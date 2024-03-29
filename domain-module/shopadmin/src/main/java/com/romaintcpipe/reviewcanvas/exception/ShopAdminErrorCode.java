package com.romaintcpipe.reviewcanvas.exception;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShopAdminErrorCode implements ErrorCode {
	SHOP_ADMIN_NOT_FOUND(400, "S001", "존재하지 않는 계정입니다.");

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
