package com.romanticpipe.reviewcanvas.admin.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class AdminNotFoundException extends BusinessException {
	public AdminNotFoundException() {
		super(ShopAdminErrorCode.ADMIN_NOT_FOUND);
	}
}
