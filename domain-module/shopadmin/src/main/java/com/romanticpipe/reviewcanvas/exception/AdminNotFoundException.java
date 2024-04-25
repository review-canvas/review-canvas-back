package com.romanticpipe.reviewcanvas.exception;

public class AdminNotFoundException extends BusinessException {
	public AdminNotFoundException() {
		super(ShopAdminErrorCode.ADMIN_NOT_FOUND);
	}
}
