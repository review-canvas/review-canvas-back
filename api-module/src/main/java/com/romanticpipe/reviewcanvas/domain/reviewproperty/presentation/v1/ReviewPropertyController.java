package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewPropertyUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetFontInfoResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForShopAdminResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewPropertyController implements ReviewPropertyApi {

	private final ReviewPropertyUseCase reviewPropertyUsecase;

	@Override
	@GetMapping("/shop-admin/review-properties")
	public ResponseEntity<SuccessResponse<GetReviewPropertyForShopAdminResponse>> getReviewPropertyForShopAdmin(
		@AuthInfo JwtInfo jwtInfo
	) {
		return SuccessResponse.of(
			reviewPropertyUsecase.getReviewPropertyForShopAdmin(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop/{mallId}/review-property")
	public ResponseEntity<SuccessResponse<GetReviewPropertyForUserResponse>> getReviewPropertyForUser(
		@PathVariable String mallId
	) {
		return SuccessResponse.of(
			reviewPropertyUsecase.getReviewPropertyForUser(mallId)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/font-info")
	public ResponseEntity<SuccessResponse<GetFontInfoResponse>> getFontInfo() {
		return SuccessResponse.of(
			reviewPropertyUsecase.getFontInfo()
		).asHttp(HttpStatus.OK);
	}

}
