package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewLike", description = "리뷰 좋아요 API")
public interface ReviewLikeApi {

	@Operation(summary = "리뷰 좋아요 취소(User) API", description = "User가 특정 리뷰에 좋아요를 취소한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 좋아요 취소(User)가 완료되었습니다.")
	})
	@DeleteMapping("/shop/{mallId}/users/{memberId}/reviews/{reviewId}/like")
	ResponseEntity<SuccessResponse<Void>> deleteReviewLikeForUser(
		@PathVariable("mallId") String mallId,
		@PathVariable("memberId") String memberId,
		@PathVariable("reviewId") long reviewId
	);

	@Operation(summary = "리뷰 좋아요 취소(ShopAdmin) API", description = "ShopAdmin이 특정 리뷰에 좋아요를 취소한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 좋아요 취소(ShopAdmin)가 완료되었습니다.")
	})
	@DeleteMapping("/shop-admin/reviews/{reviewId}/like")
	ResponseEntity<SuccessResponse<Void>> deleteReviewLikeForShopAdmin(
		@AuthInfo JwtInfo jwtInfo,
		@PathVariable("reviewId") Long reviewId
	);
}
