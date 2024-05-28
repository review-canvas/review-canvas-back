package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UpdateReplyByShopAdminRequest", description = "Shop Admin 댓글 수정 요청")
public record UpdateReplyByShopAdminRequest(
	@Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String content
) {

}
