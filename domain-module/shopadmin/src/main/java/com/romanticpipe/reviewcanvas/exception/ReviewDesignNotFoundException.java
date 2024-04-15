package com.romanticpipe.reviewcanvas.exception;

public class ReviewDesignNotFoundException extends BusinessException {
	public ReviewDesignNotFoundException() {
		super(ShopAdminErrorCode.REVIEW_THEME_NOT_FOUND);
	}
}
