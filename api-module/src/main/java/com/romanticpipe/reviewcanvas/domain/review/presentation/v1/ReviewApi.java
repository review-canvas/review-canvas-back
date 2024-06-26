package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewByShopAdminRequest;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@Operation(summary = "public view 리뷰 리스트 조회 API", description = "특정 상품의 리뷰 리스트를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/shop/{mallId}/products/{productNo}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@RequestParam(value = "memberId", required = false) String memberId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST")
		@Schema(description = "리뷰 정렬", defaultValue = "LATEST",
			allowableValues = {"LATEST", "HIGH_SCORE", "LOW_SCORE"}) ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL")
		@Schema(description = "리뷰 필터", defaultValue = "ALL",
			allowableValues = {"ALL", "IMAGE_VIDEO", "GENERAL"}) ReviewFilterForUser filter
	);

	@Operation(summary = "마이페이지 리뷰 전체 조회 API", description = "마이 페이지에서 구매후기와 관련된 모든 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 내 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/shop/{mallId}/users/{memberId}/mypage/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsInMyPage(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@RequestParam(value = "size", required = false, defaultValue = "20") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST")
		@Schema(description = "리뷰 정렬", defaultValue = "LATEST",
			allowableValues = {"LATEST", "HIGH_SCORE", "LOW_SCORE"}) ReviewSort sort,
		@RequestParam(name = "filter", required = false, defaultValue = "ALL")
		@Schema(description = "리뷰 필터", defaultValue = "ALL",
			allowableValues = {"ALL", "IMAGE_VIDEO", "GENERAL"}) ReviewFilterForUser filter
	);

	@Operation(summary = "public view 리뷰 조회 API", description = "단건 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<GetReviewDetailResponse>> getReviewForUser(
		@PathVariable Long reviewId,
		@Schema(description = "내 리뷰인지 확인하기 위해 받는 파라미터") @RequestParam(required = false) String memberId,
		@Schema(description = "내 리뷰인지 확인하기 위해 받는 파라미터") @RequestParam String mallId);

	@Operation(summary = "shop admin 대시보드 리뷰 조회 API", description = "shop admin 대시보드에서 리뷰를 조회한다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/products/{productId}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getReviewsForDashboard(
		@Schema(description = "값이 0일 시 전체 리뷰를 조회합니다.") @PathVariable("productId") Long productId,
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "sort", required = false, defaultValue = "LATEST") ReviewSort sort,
		@Schema(description = "조회기간", defaultValue = "ALL", allowableValues =
			{"ALL", "TODAY", "ONE_MONTH", "THREE_MONTH", "SIX_MONTH", "YYYY-MM-DD~YYYY-MM-DD"})
		@RequestParam(name = "period", required = false, defaultValue = "ALL") String period,
		@Schema(description = "리뷰구분")
		@RequestParam(name = "reviewFilters", required = false, defaultValue = "PHOTO,VIDEO,TEXT")
		EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		@Schema(description = "리뷰조건")
		@RequestParam(name = "score", required = false, defaultValue = "ONE,TWO,THREE,FOUR,FIVE") EnumSet<Score> score,
		@Schema(description = "답글여부")
		@RequestParam(name = "replyFilters", required = false, defaultValue = "REPLIED,NOT_REPLIED")
		EnumSet<ReplyFilter> replyFilters,
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "my page 상품 리뷰 조회 API", description = "my page에서 상품에 대한 자신의 리뷰를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 조회가 완료되었습니다.")
	})
	@GetMapping("/shop/{mallId}/users/{memberId}/mypage/{productNo}/reviews")
	ResponseEntity<SuccessResponse<PageResponse<GetReviewDetailResponse>>> getProductReviewsInMyPage(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
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

	@Operation(summary = "public view 상품 리뷰 생성 API", description = "특정 상품의 리뷰를 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품의 리뷰 생성이 완료되었습니다.")
	})
	@PostMapping(value = "/shop/{mallId}/products/{productNo}/review", consumes = "multipart/form-data")
	ResponseEntity<SuccessResponse<Void>> createReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("productNo") Long productNo,
		@Schema(description = "하단 Schemas 참고") @RequestPart CreateReviewRequest createReviewRequest,
		@Schema(description = "리뷰 이미지/동영상 MultipartFile 배열")
		@RequestPart(required = false) List<MultipartFile> reviewFiles
	);

	@Operation(summary = "리뷰 수정 API", description = "특정 상품의 리뷰를 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 수정이 완료되었습니다.")
	})
	@PatchMapping(value = "/shop/{mallId}/users/{memberId}/reviews/{reviewId}", consumes = "multipart/form-data")
	ResponseEntity<SuccessResponse<Void>> updateReview(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") Long reviewId,
		@Schema(description = "하단 Schemas 참고") @RequestPart UpdateReviewRequest updateReviewRequest,
		@Schema(description = "리뷰 이미지/동영상 MultipartFile 배열")
		@RequestPart(required = false) List<MultipartFile> reviewFiles
	);

	@Operation(summary = "리뷰 삭제 API", description = "자신이 작성한 리뷰를 삭제한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 삭제가 완료되었습니다.")
	})
	@DeleteMapping("/shop/{mallId}/users/{memberId}/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<Void>> deleteReviewByPublicView(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") Long reviewId
	);

	@Operation(summary = "Shop Admin의 상품 리뷰 생성 API", description = "Shop Admin이 특정 상품의 리뷰를 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 상품의 리뷰 생성이 완료되었습니다.")
	})
	@PostMapping(value = "/shop-admin/products/{productId}/review", consumes = "multipart/form-data")
	ResponseEntity<SuccessResponse<Void>> createReviewByShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("productId") Long productId,
		@Schema(description = "하단 Schemas 참고")
		@RequestPart CreateReviewByShopAdminRequest createReviewByShopAdminRequest,
		@Schema(description = "리뷰 이미지/동영상 MultipartFile 배열")
		@RequestPart(required = false) List<MultipartFile> reviewFiles
	);

	@Operation(summary = "Shop Admin의 리뷰 삭제 API", description = "Shop Admin이 특정 리뷰를 삭제한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 삭제가 완료되었습니다.")
	})
	@DeleteMapping("/shop-admin/reviews/{reviewId}")
	ResponseEntity<SuccessResponse<Void>> deleteReviewByShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId
	);

	@Operation(summary = "Shop Admin 리뷰 수정 API", description = "Shop Admin이 특정 리뷰를 수정한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 수정이 완료되었습니다.")
	})
	@PatchMapping(value = "/shop-admin/reviews/{reviewId}", consumes = "multipart/form-data")
	ResponseEntity<SuccessResponse<Void>> updateReviewByShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId,
		@Schema(description = "하단 Schemas 참고") @RequestPart UpdateReviewRequest updateReviewRequest,
		@Schema(description = "리뷰 이미지/동영상 MultipartFile 배열")
		@RequestPart(required = false) List<MultipartFile> reviewFiles
	);

}
