package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 API")
interface ReviewApi {

	@Operation(summary = "상품 리뷰 조회 API", description = "특정 상품의 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품의 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByProductId(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	);

	@Operation(summary = "사용자 리뷰 조회 API", description = "특정 사용자의 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 사용자의 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/users/{userId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByUserId(
		@PathVariable("userId") String userId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	);

	@Operation(summary = "리뷰 수정 API", description = "특정 상품의 리뷰를 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> updateReview(
		@PathVariable("reviewId") String productId,

		@Schema(description = "ASC, DESC 가능") Direction direction
	);
}
