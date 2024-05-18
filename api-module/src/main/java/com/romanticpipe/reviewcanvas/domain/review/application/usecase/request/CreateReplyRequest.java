package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "CreateReplyRequest", description = "댓글 생성 요청")
public record CreateReplyRequest(
	@Schema(description = "댓글을 작성할 shop의 mall id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String mallId,
	@Schema(description = "댓글을 작성할 유저의 member id", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String memberId,
	@Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String content
) {

}
