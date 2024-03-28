package com.romanticpipe.reviewcanvas.exception;

public class ReviewNotFoundException extends BusinessException {
	public ReviewNotFoundException() {
		super(ReviewErrorCode.REVIEW_NOT_FOUND);
	}
}
