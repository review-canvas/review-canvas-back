package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReviewController implements ReviewApi {

	private final ReviewUseCase reviewUseCase;

	@Override
	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByProductId(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") Direction direction
	) {
		return SuccessResponse.of(
			reviewUseCase.getReviewsByProductId(productId, PageableRequest.of(page, size, direction))
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/users/{userId}/reviews")
	public ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByUserId(
		@PathVariable("userId") String userId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC")
		@Schema(description = "ASC, DESC 가능") Direction direction
	) {
		return SuccessResponse.of(
			reviewUseCase.getReviewsByUserId(userId, PageableRequest.of(page, size, direction))
		).asHttp(HttpStatus.OK);
	}

}
