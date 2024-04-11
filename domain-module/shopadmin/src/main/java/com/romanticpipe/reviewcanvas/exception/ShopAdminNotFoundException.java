package com.romanticpipe.reviewcanvas.exception;

public class ShopAdminNotFoundException extends BusinessException {
	public ShopAdminNotFoundException() {
		super(ShopAdminErrorCode.ADMIN_NOT_FOUND);
	}
}
