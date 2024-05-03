package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewColumnNotFoundException extends BusinessException {
	public ReviewColumnNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_COLUMN_NOT_FOUND);
	}
}
