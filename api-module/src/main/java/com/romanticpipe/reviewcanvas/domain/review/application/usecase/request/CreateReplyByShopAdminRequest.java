package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "CreateReplyByShopAdminRequest", description = "Shop Admin 댓글 생성 요청")
public record CreateReplyByShopAdminRequest(
	@Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String content
) {

}
