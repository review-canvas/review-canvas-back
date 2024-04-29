package com.romanticpipe.reviewcanvas.admin.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ShopAuthTokenNotFoundException extends BusinessException {
	public ShopAuthTokenNotFoundException() {
		super(ShopAdminErrorCode.SHOP_AUTH_TOKEN_NOT_FOUND);
	}
}
