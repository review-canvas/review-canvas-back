package com.romanticpipe.reviewcanvas.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.romanticpipe.reviewcanvas.domain.Reply;

import java.util.List;

public record ReviewInfo(Long reviewId, String content, Integer score, Long userId, String nickname,
						 List<Reply> replies) {

	@QueryProjection
	public ReviewInfo {
	}
}
