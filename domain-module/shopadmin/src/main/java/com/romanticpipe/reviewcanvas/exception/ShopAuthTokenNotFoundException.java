package com.romanticpipe.reviewcanvas.exception;

public class ShopAuthTokenNotFoundException extends BusinessException {
	public ShopAuthTokenNotFoundException() {
		super(ShopAdminErrorCode.SHOP_AUTH_TOKEN_NOT_FOUND);
	}
}
