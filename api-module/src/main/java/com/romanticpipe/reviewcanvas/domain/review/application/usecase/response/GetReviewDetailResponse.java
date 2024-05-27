package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	@Schema(description = "리뷰 작성자 id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Long userId,
	@Schema(description = "리뷰 작성 shop admin id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer shopAdminId,
	@Schema(description = "리뷰 작성자 닉네임", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String nickname,
	@Schema(description = "본인 작성 리뷰 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean isMine,
	@Schema(description = "리뷰 생성 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime createAt,
	@Schema(description = "리뷰 수정 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime updatedAt,
	@Schema(description = "리뷰 삭제 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean deleted,
	@Schema(description = "리뷰가 작성된 상품 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long productId,
	@Schema(description = "리뷰가 작성된 상품 이름", requiredMode = Schema.RequiredMode.REQUIRED)
	String productName,
	@Schema(description = "리뷰에 해당하는 댓글 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<ReplyResponse> replies
) {

	public static GetReviewDetailResponse from(Review review, boolean isMine, String memberId) {
		if (review.getUser() == null) {
			return GetReviewDetailResponse.builder()
				.reviewId(review.getId())
				.content(review.getDeletedAt() == null ? review.getContent() : " ")
				.score(review.getScore())
				.shopAdminId(review.getShopAdminId())
				.isMine(isMine)
				.createAt(review.getCreatedAt())
				.updatedAt(review.getUpdatedAt())
				.deleted(review.getDeletedAt() != null)
				.productId(review.getProduct().getId())
				.productName(review.getProduct().getName())
				.replies(review.getReplyList().stream().map(reply -> ReplyResponse.from(reply,
					Optional.ofNullable(reply.getUser())
						.map(user -> user.getMemberId().equals(memberId))
						.orElse(false))).toList())
				.build();
		}
		return GetReviewDetailResponse.builder()
			.reviewId(review.getId())
			.content(review.getDeletedAt() == null ? review.getContent() : " ")
			.score(review.getScore())
			.userId(review.getUser().getId())
			.nickname(review.getUser().getNickName())
			.isMine(isMine)
			.createAt(review.getCreatedAt())
			.updatedAt(review.getUpdatedAt())
			.deleted(review.getDeletedAt() != null)
			.productId(review.getProduct().getId())
			.productName(review.getProduct().getName())
			.replies(review.getReplyList().stream().map(reply -> ReplyResponse.from(reply,
				Optional.ofNullable(reply.getUser())
					.map(user -> user.getMemberId().equals(memberId))
					.orElse(false))).toList())
			.build();
	}
}
