package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateReplyResponse", description = "댓글 생성 응답")
public record CreateReplyResponse(@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
								  Long replyId) {

	public CreateReplyResponse {
		Objects.requireNonNull(replyId);
	}

}
