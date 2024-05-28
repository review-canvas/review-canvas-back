package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewLike", description = "리뷰 좋아요 API")
public interface ReviewLikeApi {

	@Operation(summary = "리뷰 좋아요 개수 조회 API", description = "특정 리뷰의 좋아요 개수를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 좋아요 개수 조회가 완료되었습니다.")
	})
	@GetMapping("/reviews/{reviewId}/like/count")
	ResponseEntity<SuccessResponse<Map<String, Integer>>> getReviewLikeCount(
		@PathVariable("reviewId") Long reviewId
	);
}
