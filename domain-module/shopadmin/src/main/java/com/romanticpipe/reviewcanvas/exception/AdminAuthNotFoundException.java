package com.romanticpipe.reviewcanvas.exception;

public class AdminAuthNotFoundException extends BusinessException {
	public AdminAuthNotFoundException() {
		super(ShopAdminErrorCode.ADMIN_AUTH_NOT_FOUND);
	}
}
