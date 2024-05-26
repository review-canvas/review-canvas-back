package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

	// Review
	REVIEW_NOT_FOUND(400, "R001", "리뷰를 찾을 수 없습니다."),

	// Product
	PRODUCT_NOT_FOUND(400, "R002", "상품을 찾을 수 없습니다."),

	// User
	USER_NOT_FOUND(400, "R003", "유저를 찾을 수 없습니다."),
	ADMIN_NOT_MATCH(400, "R004", "자신 Shop의 글이 아닙니다"),

	// Reply
	REPLY_NOT_FOUND(400, "R005", "댓글을 찾을 수 없습니다.");

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
