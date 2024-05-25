package com.romanticpipe.reviewcanvas.exception;

public class ReviewNotMatchAdminException extends BusinessException {
	public ReviewNotMatchAdminException() {
		super(ReviewErrorCode.ADMIN_NOT_MATCH);
	}
}
