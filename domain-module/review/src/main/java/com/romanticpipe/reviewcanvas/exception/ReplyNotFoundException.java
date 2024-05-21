package com.romanticpipe.reviewcanvas.exception;

public class ReplyNotFoundException extends BusinessException {
	public ReplyNotFoundException() {
		super(ReviewErrorCode.REPLY_NOT_FOUND);
	}
}
