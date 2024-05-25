package com.romanticpipe.reviewcanvas.exception;

public class ReplyNotFoundException extends BusinessException {
	public ReplyNotFoundException() {
		super(ReplyErrorCode.REPLY_NOT_FOUND);
	}
}
