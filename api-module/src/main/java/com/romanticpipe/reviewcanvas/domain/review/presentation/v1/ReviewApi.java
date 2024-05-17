package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 API")
interface ReviewApi {

	@Operation(summary = "상품 리뷰 리스트 조회 API", description = "특정 상품의 리뷰 리스트를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/shop/{mallId}/products/{productNo}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewForUserResponse>>> getReviewsForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST")
		@Schema(description = "리뷰 정렬", defaultValue = "LATEST",
			allowableValues = {"LATEST", "HIGH_SCORE", "LOW_SCORE"}) ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL")
		@Schema(description = "리뷰 필터", defaultValue = "ALL",
			allowableValues = {"ALL", "IMAGE_VIDEO", "GENERAL"}) ReviewFilter filter
	);

	@Operation(summary = "리뷰 조회 API", description = "단건 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<GetReviewForUserResponse>> getReviewsForUser(@PathVariable Long reviewId);

	@Operation(summary = "상품 리뷰 생성 API", description = "특정 상품의 리뷰를 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품의 리뷰 생성이 완료되었습니다.")
	})
	@PostMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<Void>> createReview(
		@PathVariable("productId") Long productId,
		@RequestBody CreateReviewRequest createReviewRequest
	);

	@Operation(summary = "리뷰 수정 API", description = "특정 상품의 리뷰를 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@PatchMapping("/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<Void>> updateReview(
		@PathVariable("reviewId") long productId,
		@RequestBody UpdateReviewRequest updateReviewRequest
	);

	@Operation(summary = "사용자 리뷰 조회 API", description = "특정 사용자의 리뷰를 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 사용자 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/users/{userId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewResponse>>> getReviewsByUserId(
		@PathVariable("userId") String userId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "reviewSort", required = false, defaultValue = "DESC")
		@Schema(description = "ASC, DESC 가능") ReviewSort reviewSort
	);

}
