package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest, MultipartFile logoImage);

	GetReviewVisibilityTitleResponse getReviewVisibilityTitle();

	boolean emailCheck(String email);

	List<ReviewDesign> getGeneralReviewThemeList();

}
