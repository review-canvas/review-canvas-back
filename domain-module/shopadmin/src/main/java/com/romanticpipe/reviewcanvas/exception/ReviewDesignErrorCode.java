package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewDesignErrorCode implements ErrorCode {
	REVIEW_DESIGN_INVALID_POSITION(400, "RD001", "유효하지 않은 리뷰 디자인 포지션입니다.");

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
