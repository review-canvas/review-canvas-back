package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewPropertyErrorCode implements ErrorCode {

	REVIEW_LAYOUT_NOT_FOUND(400, "RP001", "해당 계정에 Layout이 존재하지 않습니다."),
	REVIEW_TITLE_NOT_FOUND(400, "RP002", "해당 계정에 Title이 존재하지 않습니다."),
	REVIEW_DESCRIPTION_NOT_FOUND(400, "RP003", "해당 계정에 Description이 존재하지 않습니다."),
	REVIEW_CONTAINER_NOT_FOUND(400, "RP004", "해당 계정에 Container가 존재하지 않습니다."),
	REVIEW_COLUMN_NOT_FOUND(400, "RP005", "해당 계정에 Column이 존재하지 않습니다.");

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
