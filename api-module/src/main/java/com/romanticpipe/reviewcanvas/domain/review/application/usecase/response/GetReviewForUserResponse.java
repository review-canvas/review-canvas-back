package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.dto.ReviewInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetReviewForUserResponse", description = "public view의 유저에게 보일 리뷰 조회 response")
public record GetReviewForUserResponse(
	@Schema(description = "리뷰 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long reviewId,
	@Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
	String content,
	@Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer score,
	@Schema(description = "리뷰 작성자 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long userId,
	@Schema(description = "리뷰 작성자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
	String nickname
) {

	public static GetReviewForUserResponse from(ReviewInfo reviewInfo) {
		return GetReviewForUserResponse.builder()
			.reviewId(reviewInfo.reviewId())
			.content(reviewInfo.content())
			.score(reviewInfo.score())
			.userId(reviewInfo.userId())
			.nickname(reviewInfo.nickname())
			.build();
	}
}
