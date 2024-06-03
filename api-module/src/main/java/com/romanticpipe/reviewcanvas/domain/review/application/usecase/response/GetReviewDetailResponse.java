package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
	@Schema(description = "본인(user) 작성 리뷰 여부 [shop admin 해당사항 없음]", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean isMine,
	@Schema(description = "좋아요 수", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer likeCount,
	@Schema(description = "본인(user 또는 shop admin)이 좋아요를 눌렀는지 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean isLiked,
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
	@Schema(description = "리뷰 이미지/비디오 url dto", requiredMode = Schema.RequiredMode.REQUIRED)
	FileContentsResponse imageVideoUrls,
	@Schema(description = "리뷰에 해당하는 댓글 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<ReplyResponse> replies
) {

	public static GetReviewDetailResponse forUser(Review review, Long requestUserId,
												  FileContentsResponse fileContentsResponse,
												  int reviewLikeCount, boolean isLikeThisReview) {
		boolean isThisRequestUserReview = !review.isShopAdminReview()
			&& Objects.equals(requestUserId, review.getUser().getId());
		String content = review.getDeletedAt() == null ? review.getContent() : " ";
		var replyResponses = review.getReplyList().stream()
			.map(reply -> createReplyResponse(requestUserId, reply))
			.toList();

		var builder = GetReviewDetailResponse.builder()
			.reviewId(review.getId())
			.content(content)
			.score(review.getScore())
			.isMine(isThisRequestUserReview)
			.likeCount(reviewLikeCount)
			.isLiked(isLikeThisReview)
			.createAt(review.getCreatedAt())
			.updatedAt(review.getUpdatedAt())
			.deleted(review.getDeletedAt() != null)
			.productId(review.getProduct().getId())
			.productName(review.getProduct().getName())
			.imageVideoUrls(fileContentsResponse)
			.replies(replyResponses);

		return addDetails(builder, review).build();
	}

	public static GetReviewDetailResponse forShopAdmin(Review review, FileContentsResponse fileContentsResponse,
													   int reviewLikeCount, boolean isLikeThisReview) {
		var replyResponses = review.getReplyList().stream()
			.map(reply -> ReplyResponse.from(reply, false))
			.toList();

		var builder = GetReviewDetailResponse.builder()
			.reviewId(review.getId())
			.content(review.getContent())
			.score(review.getScore())
			.isMine(false)
			.likeCount(reviewLikeCount)
			.isLiked(isLikeThisReview)
			.createAt(review.getCreatedAt())
			.updatedAt(review.getUpdatedAt())
			.deleted(review.getDeletedAt() != null)
			.productId(review.getProduct().getId())
			.productName(review.getProduct().getName())
			.imageVideoUrls(fileContentsResponse)
			.replies(replyResponses);

		return addDetails(builder, review).build();
	}

	private static GetReviewDetailResponse.GetReviewDetailResponseBuilder addDetails(
		GetReviewDetailResponse.GetReviewDetailResponseBuilder builder, Review review) {
		if (review.isShopAdminReview()) {
			builder.shopAdminId(review.getShopAdminId());
		} else {
			builder.userId(review.getUser().getId())
				.nickname(review.getUser().getNickName());
		}
		return builder;
	}

	private static ReplyResponse createReplyResponse(Long requestUserId, Reply reply) {
		boolean isRequestUserReply = Objects.equals(requestUserId, reply.getUser().getId());
		return ReplyResponse.from(reply, isRequestUserReply);
	}
}
