package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewLikeRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewLike", description = "리뷰 좋아요 API")
public interface ReviewLikeApi {

	@Operation(summary = "리뷰 좋아요 생성(User) API", description = "User가 특정 리뷰의 좋아요를 누른다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 좋아요 생성(User)이 완료되었습니다.")
	})
	@PostMapping("/reviews/{reviewId}/like/")
	ResponseEntity<SuccessResponse<Void>> createReviewLike(
		@PathVariable("reviewId") Long reviewId,
		@RequestBody CreateReviewLikeRequest createReviewLikeRequest
	);
}
