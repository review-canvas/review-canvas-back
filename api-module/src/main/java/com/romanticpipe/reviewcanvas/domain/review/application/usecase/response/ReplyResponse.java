package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.Reply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReplyResponse(
	@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long replyId,
	@Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
	String content,
	@Schema(description = "댓글 작성자 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long userId,
	@Schema(description = "댓글 작성자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
	String nickname
) {

	public static ReplyResponse from(Reply reply) {
		return ReplyResponse.builder()
			.replyId(reply.getId())
			.content(reply.getContent())
			.userId(reply.getUser().getId())
			.nickname(reply.getUser().getNickName())
			.build();
	}
}
