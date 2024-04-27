package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import java.util.List;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	GetReviewVisibilityTitleResponse getReviewVisibilityTitle();

	boolean emailCheck(String email);

	List<ReviewDesign> getGeneralReviewThemeList();

	void updateReviewDesign(Integer adminId, Integer reviewDesignId,
		UpdateReviewDesignRequest updateReviewDesignRequest);
}
