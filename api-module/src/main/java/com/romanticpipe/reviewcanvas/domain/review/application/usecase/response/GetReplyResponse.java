package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.time.LocalDateTime;

import com.romanticpipe.reviewcanvas.domain.Reply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record GetReplyResponse(@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
							   Long replyId,
							   @Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
							   String content,
							   @Schema(description = "댓글 생성 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
							   LocalDateTime createAt,
							   @Schema(description = "댓글 수정 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
							   LocalDateTime updatedAt,
							   @Schema(description = "댓글 삭제 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
							   LocalDateTime deletedAt,
							   @Schema(description = "작성자 아이디", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
							   String memberId,
							   @Schema(description = "작성자 닉네임", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
							   String nickName,
							   @Schema(description = "작성 shopAdmin id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
							   Integer shopAdminId) {

	public static GetReplyResponse from(Reply reply) {
		if (reply.getUser() == null) {
			return GetReplyResponse.builder()
				.replyId(reply.getId())
				.content(reply.getDeletedAt() == null ? reply.getContent() : " ")
				.createAt(reply.getCreatedAt())
				.updatedAt(reply.getUpdatedAt())
				.deletedAt(reply.getDeletedAt())
				.shopAdminId(reply.getShopAdminId())
				.build();
		} else {
			return GetReplyResponse.builder()
				.replyId(reply.getId())
				.content(reply.getDeletedAt() == null ? reply.getContent() : " ")
				.createAt(reply.getCreatedAt())
				.updatedAt(reply.getUpdatedAt())
				.deletedAt(reply.getDeletedAt())
				.memberId(reply.getUser().getMemberId())
				.nickName(reply.getUser().getNickName())
				.build();
		}
	}
}
