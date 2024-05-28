package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewLikeUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLikeController implements ReviewLikeApi {

	private final ReviewLikeUseCase reviewLikeUseCase;

	@Override
	@PostMapping("/reviews/{reviewId}/like/")
	public ResponseEntity<SuccessResponse<Void>> createReviewLike(
		@PathVariable("reviewId") Long reviewId,
		@RequestBody CreateReviewLikeRequest createReviewLikeRequest
	) {
		reviewLikeUseCase.createReviewLike(reviewId, createReviewLikeRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
