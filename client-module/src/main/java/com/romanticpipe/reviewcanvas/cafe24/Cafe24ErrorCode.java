package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Cafe24ErrorCode implements ErrorCode {

	INVALID_OR_EXPIRED_REFRESH_TOKEN(400, "CA001", "cafe24 refresh token이 유효하지 않거나 만료되었습니다."),
	PRODUCT_NOT_FOUND(400, "CA002", "CAFE24 상품을 찾을 수 없습니다."),
	INVALID_ACCESS_TOKEN(400, "CA003", "CAFE24 access token이 유효하지 않습니다.");

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
