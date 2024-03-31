package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateCommentRequest", description = "댓글 생성 요청")
public record CreateReplyRequest(@Schema(description = "리뷰 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
								 int reviewId,
								 @Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
								 String content,
								 @Schema(description = "댓글 작성자 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
								 String userId) {
}
