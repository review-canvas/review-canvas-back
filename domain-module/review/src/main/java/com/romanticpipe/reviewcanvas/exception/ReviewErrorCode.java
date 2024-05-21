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

	// Reply
	REPLY_NOT_FOUND(400, "R003", "댓글을 찾을 수 없습니다."),
	WRITER_NOT_MATCH(400, "R004", "댓글의 작성자가 아닙니다."),
	REPLY_CAN_NOT_UPDATE(400, "R005", "댓글을 수정할 수 없습니다");
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
