package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForShopAdminResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForUserResponse;

public interface ReviewPropertyUseCase {

	GetReviewPropertyForShopAdminResponse getReviewPropertyForShopAdmin(Integer shopAdminId);

	GetReviewPropertyForUserResponse getReviewPropertyForUser(String mallId);
}
