package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewLayoutUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.ReviewLayoutResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLayoutController implements ReviewLayoutApi {

	private final ReviewLayoutUseCase reviewLayoutUsecase;

	@Override
	@PatchMapping("/shop-admin/review-layout")
	public ResponseEntity<SuccessResponse<Void>> updateLayout(@AuthInfo JwtInfo jwtInfo,
															  @RequestBody UpdateLayoutRequest updateLayoutRequest) {

		reviewLayoutUsecase.updateLayout(jwtInfo.adminId(), updateLayoutRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-layout")
	public ResponseEntity<SuccessResponse<ReviewLayoutResponse>> getReviewLayout(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(reviewLayoutUsecase.getReviewLayout(jwtInfo.adminId())).asHttp(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SuccessResponse<Void>> initializeReviewLayout(JwtInfo jwtInfo) {
		return null;
	}
}
