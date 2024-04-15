package com.romanticpipe.reviewcanvas.exception;

public class AdminNotFoundException extends BusinessException {
	public AdminNotFoundException() {
		super(AdminErrorCode.ADMIN_NOT_FOUND);
	}
}
