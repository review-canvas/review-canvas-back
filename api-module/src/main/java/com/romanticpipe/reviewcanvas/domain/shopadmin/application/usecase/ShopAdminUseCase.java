package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.SignUpRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetReviewVisibilityTitleResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShopAdminUseCase {

	void signUp(SignUpRequest signUpRequest, MultipartFile logoImage);

	GetReviewVisibilityTitleResponse getReviewVisibilityTitle();

	boolean emailCheck(String email);

	List<ReviewDesign> getGeneralReviewThemeList();

	void updateReviewDesign(Integer adminId, Integer reviewDesignId,
							UpdateReviewDesignRequest updateReviewDesignRequest);

	ShopAdmin getShopAdmin(Integer shopAdminId);
}
