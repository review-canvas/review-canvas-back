package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetWaitingReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 API")
interface ReviewApi {

	@Operation(summary = "리뷰 조회 API", description = "특정 상품의 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviews(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	);

	@Operation(summary = "승인 대기 중 리뷰 조회 API", description = "승인 대기중인 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다."
		)
	})
	@GetMapping("/products/{productId}/waiting")
	ResponseEntity<SuccessResponse<PageResponse<GetWaitingReviewResponse>>> getWaitingReviews(
		@PathVariable("productId") String productId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	);
}
