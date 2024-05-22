package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

	// Review
	REVIEW_NOT_FOUND(400, "R001", "리뷰를 찾을 수 없습니다."),

	// Product
	PRODUCT_NOT_FOUND(400, "R002", "상품을 찾을 수 없습니다."),
	USER_NOT_FOUND(400, "R001", "사용자를 찾을 수 없습니다.");

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
