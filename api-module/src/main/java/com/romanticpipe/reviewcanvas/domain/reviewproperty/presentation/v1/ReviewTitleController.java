package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewTitleUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateReviewTitleRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewTitleResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewTitleController implements ReviewTitleApi {

	private final ReviewTitleUseCase reviewTitleUseCase;

	@Override
	@PatchMapping("/shop-admin/review-title")
	public ResponseEntity<SuccessResponse<Void>> updateReviewTitle(
		@AuthInfo JwtInfo jwtInfo, @RequestBody UpdateReviewTitleRequest updateReviewTitleRequest) {
		reviewTitleUseCase.updateReviewTitle(jwtInfo.adminId(),
			updateReviewTitleRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-title/reset")
	public ResponseEntity<SuccessResponse<Void>> initializeReviewTitle(@AuthInfo JwtInfo jwtInfo) {
		reviewTitleUseCase.initializeReviewTitle(jwtInfo.adminId());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-title")
	public ResponseEntity<SuccessResponse<GetReviewTitleResponse>> getReviewTitle(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			reviewTitleUseCase.getReviewTitle(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}
}
