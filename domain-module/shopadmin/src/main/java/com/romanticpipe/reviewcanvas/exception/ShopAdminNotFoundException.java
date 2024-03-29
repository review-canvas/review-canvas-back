package com.romanticpipe.reviewcanvas.exception;

public class ShopAdminNotFoundException extends BusinessException {
	public ShopAdminNotFoundException() {
		super(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND);
	}
}
