package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewPropertyUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewPropertyController implements ReviewPropertyApi {

	private final ReviewPropertyUseCase reviewPropertyUsecase;

	@Override
	@GetMapping("/shop-admin/review-properties")
	public ResponseEntity<SuccessResponse<GetReviewPropertyResponse>> getAllReviewProperty(JwtInfo jwtInfo) {
		return SuccessResponse.of(
			reviewPropertyUsecase.getAllReviewProperty(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}

}
