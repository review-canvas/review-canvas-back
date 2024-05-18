package com.romanticpipe.reviewcanvas.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReplyErrorCode implements ErrorCode {

	// Review
	REPLY_NOT_FOUND(400, "RP001", "댓글을 찾을 수 없습니다."),
	WRITER_NOT_MATCH(400, "RP002", "댓글의 작성자가 아닙니다.");

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
