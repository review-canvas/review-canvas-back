package com.romanticpipe.reviewcanvas.exception;

public class UserNotFoundException extends BusinessException {
	public UserNotFoundException() {
		super(ReviewErrorCode.USER_NOT_FOUND);
	}
}
