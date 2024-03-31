package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetWaitingReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReviewController implements ReviewApi {

	private final ReviewUseCase reviewUseCase;

	@Override
	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviews(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	) {
		return SuccessResponse.of(
			reviewUseCase.getReviews(productId, PageableRequest.of(page, size, direction))
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/products/{productId}/waiting")
	public ResponseEntity<SuccessResponse<PageResponse<GetWaitingReviewResponse>>> getWaitingReviews(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction) {
		return SuccessResponse.of(
			reviewUseCase.getWaitingReviews(productId, PageableRequest.of(page, size, direction))
		).asHttp(HttpStatus.OK);
	}

}
