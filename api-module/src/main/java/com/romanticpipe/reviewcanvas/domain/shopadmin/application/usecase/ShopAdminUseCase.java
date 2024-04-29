package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;

import java.util.List;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest);

	GetReviewVisibilityTitleResponse getReviewVisibilityTitle();

	boolean emailCheck(String email);

	List<ReviewDesign> getGeneralReviewThemeList();

	List<ReviewDesign> getCustomReviewThemeList(Integer shopAdminId);
}
