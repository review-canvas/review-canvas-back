package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewDesignViewNotFoundException extends BusinessException {
	public ReviewDesignViewNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_DESIGN_VIEW_NOT_FOUND);
	}
}
