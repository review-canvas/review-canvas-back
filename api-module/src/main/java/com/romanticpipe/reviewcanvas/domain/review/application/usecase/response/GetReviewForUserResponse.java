package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

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
	String nickname,
	@Schema(description = "리뷰에 해당하는 댓글 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<ReplyResponse> replies
) {

	public static GetReviewForUserResponse from(Review review) {
		return GetReviewForUserResponse.builder()
			.reviewId(review.getId())
			.content(review.getContent())
			.score(review.getScore())
			.userId(review.getUser().getId())
			.nickname(review.getUser().getNickName())
			.replies(review.getReplyList().stream().map(ReplyResponse::from).toList())
			.build();
	}
}
