package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyResponse;

public interface ReviewPropertyUseCase {

	GetReviewPropertyResponse getAllReviewProperty(Integer shopAdminId);
}
