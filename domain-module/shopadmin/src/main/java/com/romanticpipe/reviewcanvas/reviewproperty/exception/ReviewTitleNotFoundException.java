package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewTitleNotFoundException extends BusinessException {
	public ReviewTitleNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_TITLE_NOT_FOUND);
	}
}
