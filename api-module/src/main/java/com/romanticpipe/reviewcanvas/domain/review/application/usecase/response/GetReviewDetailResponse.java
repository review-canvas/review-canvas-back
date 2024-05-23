package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.util.List;

import com.romanticpipe.reviewcanvas.domain.Review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetReviewDetailResponse", description = "리뷰 및 댓글 조회 response")
public record GetReviewDetailResponse(
	@Schema(description = "리뷰 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long reviewId,
	@Schema(description = "리뷰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
	String content,
	@Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer score,
	@Schema(description = "리뷰 작성자 cafe24 id", requiredMode = Schema.RequiredMode.REQUIRED)
	String memberId,
	@Schema(description = "리뷰 작성자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
	String nickname,
	@Schema(description = "본인 작성 리뷰 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean isMine,
	@Schema(description = "리뷰에 해당하는 댓글 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<ReplyResponse> replies
) {

	public static GetReviewDetailResponse from(Review review, boolean isMine) {
		return GetReviewDetailResponse.builder()
			.reviewId(review.getId())
			.content(review.getDeletedAt() == null ? review.getContent() : " ")
			.score(review.getScore())
			.memberId(review.getUser().getMemberId())
			.nickname(review.getUser().getNickName())
			.isMine(isMine)
			.replies(review.getReplyList().stream().map(ReplyResponse::from).toList())
			.build();
	}
}
