package com.romanticpipe.reviewcanvas.admin.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class AdminAuthNotFoundException extends BusinessException {
	public AdminAuthNotFoundException() {
		super(ShopAdminErrorCode.ADMIN_AUTH_NOT_FOUND);
	}
}
