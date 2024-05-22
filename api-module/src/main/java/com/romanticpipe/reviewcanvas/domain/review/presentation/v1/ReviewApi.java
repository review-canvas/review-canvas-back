package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.EnumSet;
import java.util.List;

@Tag(name = "Review", description = "리뷰 API")
interface ReviewApi {

	@Operation(summary = "상품 리뷰 리스트 조회 API", description = "특정 상품의 리뷰 리스트를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/shop/{mallId}/products/{productNo}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST")
		@Schema(description = "리뷰 정렬", defaultValue = "LATEST",
			allowableValues = {"LATEST", "HIGH_SCORE", "LOW_SCORE"}) ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL")
		@Schema(description = "리뷰 필터", defaultValue = "ALL",
			allowableValues = {"ALL", "IMAGE_VIDEO", "GENERAL"}) ReviewFilterForUser filter
	);

	@Operation(summary = "리뷰 조회 API", description = "단건 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<GetReviewDetailResponse>> getReviewForUser(@PathVariable Long reviewId);

	@Operation(summary = "shop admin 대시보드 리뷰 조회 API", description = "shop admin 대시보드에서 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForDashboard(
		@PathVariable("productId") Long productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST") ReviewSort sort,
		@Schema(description = "리뷰구분")
		@RequestParam(name = "reviewFilters", required = false, defaultValue = "PHOTO,VIDEO,TEXT")
		EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		@Schema(description = "리뷰조건")
		@RequestParam(name = "score", required = false, defaultValue = "ONE,TWO,THREE,FOUR,FIVE") EnumSet<Score> score,
		@Schema(description = "답글여부")
		@RequestParam(name = "replyFilters", required = false, defaultValue = "REPLIED,NOT_REPLIED")
		EnumSet<ReplyFilter> replyFilters
	);

	@Operation(summary = "상품 리뷰 생성 API", description = "특정 상품의 리뷰를 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품의 리뷰 생성이 완료되었습니다.")
	})
	@PostMapping("/shop/{mallId}/products/{productNo}/review")
	ResponseEntity<SuccessResponse<Void>> createReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productId,
		@RequestPart CreateReviewRequest createReviewRequest,
		@RequestPart(required = false) List<MultipartFile> reviewImages
	);

	@Operation(summary = "리뷰 수정 API", description = "특정 상품의 리뷰를 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 수정이 완료되었습니다.")
	})
	@PatchMapping("/shop/{mallId}/users/{memberId}/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<Void>> updateReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") long reviewId,
		@RequestPart UpdateReviewRequest updateReviewRequest,
		@RequestPart(required = false) List<MultipartFile> reviewImages
	);
}
