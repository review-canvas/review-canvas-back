package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReviewController implements ReviewApi {

	private final ReviewUseCase reviewUseCase;

	@Override
	@GetMapping("/shop/{mallId}/products/{productNo}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewForUserResponse>>> getReviewsForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST") ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL") ReviewFilter filter) {
		return SuccessResponse.of(
			reviewUseCase.getReviewsForUser(mallId, productNo, PageableRequest.of(page, size, sort), filter)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<SuccessResponse<GetReviewForUserResponse>> getReviewsForUser(
		@PathVariable Long reviewId) {
		return SuccessResponse.of(
			reviewUseCase.getReviewForUser(reviewId)
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/users/{userId}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByUserId(
		@PathVariable("userId") String userId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "reviewSort", required = false, defaultValue = "DESC")
		@Schema(description = "ASC, DESC 가능") ReviewSort reviewSort
	) {
		return SuccessResponse.of(
			reviewUseCase.getReviewsByUserId(userId, PageableRequest.of(page, size, reviewSort))
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PostMapping("/products/{productId}/reviews")
	public ResponseEntity<SuccessResponse<Void>> createReview(
		@PathVariable("productId") String productId, @RequestBody CreateReviewRequest createReviewRequest) {
		reviewUseCase.createReview(productId, createReviewRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/reviews/{reviewId}")
	public ResponseEntity<SuccessResponse<Void>> updateReview(long reviewId,
															  UpdateReviewRequest updateReviewRequest) {
		reviewUseCase.updateReview(reviewId, updateReviewRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

}
