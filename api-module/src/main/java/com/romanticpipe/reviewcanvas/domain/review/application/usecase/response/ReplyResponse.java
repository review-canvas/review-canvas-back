package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.Reply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReplyResponse(
	@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
	Long replyId,
	@Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
	String content,
	@Schema(description = "댓글 생성 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime createAt,
	@Schema(description = "댓글 수정 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime updatedAt,
	@Schema(description = "댓글 삭제 여부", requiredMode = Schema.RequiredMode.REQUIRED)
	Boolean deleted,
	@Schema(description = "작성자 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
	Long userId,
	@Schema(description = "작성자 mall 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
	String mallId,
	@Schema(description = "작성자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
	String nickname) {

	public static ReplyResponse from(Reply reply) {
		return ReplyResponse.builder()
			.replyId(reply.getId())
			.content(reply.getDeletedAt() == null ? reply.getContent() : " ")
			.createAt(reply.getCreatedAt())
			.updatedAt(reply.getUpdatedAt())
			.deleted(reply.getDeletedAt() != null)
			.userId(reply.getUser().getId())
			.mallId(reply.getUser().getMallId())
			.nickname(reply.getUser().getNickName())
			.build();
	}
}
