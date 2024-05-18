package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import java.time.LocalDateTime;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetReplyForUserResponse(@Schema(description = "댓글 id", requiredMode = Schema.RequiredMode.REQUIRED)
									  Long replyId,
									  @Schema(description = "댓글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
									  String content,
									  @Schema(description = "댓글 생성 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
									  LocalDateTime createAt,
									  @Schema(description = "댓글 수정 날짜", requiredMode = Schema.RequiredMode.REQUIRED)
									  LocalDateTime updatedAt,
									  @Schema(description = "댓글을 작성한 user 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
									  String memberId,
									  @Schema(description = "댓글을 작성한 user 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
									  String nickName) {

	public static GetReplyForUserResponse from(Reply reply, User user) {
		return new GetReplyForUserResponse(reply.getId(), reply.getContent(), reply.getCreatedAt(),
			reply.getUpdatedAt(),
			user.getMemberId(), user.getNickName());
	}
}
