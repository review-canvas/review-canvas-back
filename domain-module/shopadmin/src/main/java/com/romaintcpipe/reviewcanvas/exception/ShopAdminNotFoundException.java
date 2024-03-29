package com.romaintcpipe.reviewcanvas.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ShopAdminNotFoundException extends BusinessException {
	public ShopAdminNotFoundException() {
		super(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND);
	}
}
