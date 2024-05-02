package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewContainerNotFoundException extends BusinessException {

	public ReviewContainerNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_CONTAINER_NOT_FOUND);
	}
}
