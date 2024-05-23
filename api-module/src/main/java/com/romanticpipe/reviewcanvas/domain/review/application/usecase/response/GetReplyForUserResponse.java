package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.time.LocalDateTime;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record GetReplyForUserResponse(@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
									  Long replyId,
									  @Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
									  String content,
									  @Schema(description = "댓글 생성 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
									  LocalDateTime createAt,
									  @Schema(description = "댓글 수정 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
									  LocalDateTime updatedAt,
									  @Schema(description = "댓글 삭제 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
									  LocalDateTime deletedAT,
									  @Schema(description = "작성자 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
									  String memberId,
									  @Schema(description = "작성자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
									  String nickName) {

	public static GetReplyForUserResponse from(Reply reply, User user) {
		return GetReplyForUserResponse.builder()
			.replyId(reply.getId())
			.content(reply.getDeletedAt() == null ? reply.getContent() : " ")
			.createAt(reply.getCreatedAt())
			.updatedAt(reply.getUpdatedAt())
			.deletedAT(reply.getDeletedAt())
			.memberId(user.getMemberId())
			.nickName(user.getNickName())
			.build();
	}
}
