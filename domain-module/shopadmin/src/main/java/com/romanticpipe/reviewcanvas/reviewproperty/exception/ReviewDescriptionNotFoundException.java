package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewDescriptionNotFoundException extends BusinessException {
	public ReviewDescriptionNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_DESCRIPTION_NOT_FOUND);
	}
}
