package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record FileContentsResponse(
	@Schema(description = "리뷰 이미지/동영상 presigned url 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<String> reviewFileUrls,
	@Schema(description = "리사이징된 리뷰 이미지 url 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
	List<String> reviewResizeImageUrls
) {

	public static FileContentsResponse of(List<String> reviewFileUrls, List<String> reviewResizeImageUrls) {
		return new FileContentsResponse(reviewFileUrls, reviewResizeImageUrls);
	}

	public static FileContentsResponse empty() {
		return new FileContentsResponse(List.of(), List.of());
	}
}
