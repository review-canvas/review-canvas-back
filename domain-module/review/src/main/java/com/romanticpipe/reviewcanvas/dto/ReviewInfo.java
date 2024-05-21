package com.romanticpipe.reviewcanvas.dto;

import com.querydsl.core.annotations.QueryProjection;

public record ReviewInfo(Long reviewId, String content, Integer score, Long userId, String nickname) {

	@QueryProjection
	public ReviewInfo {
	}
}
