package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetSelectedReviewDesignResponse", description = "선택 중 리뷰 디자인 조회 응답")
public record GetSelectedReviewDesignResponse(
									@Schema(description = "리뷰 디자인 타입", requiredMode = Schema.RequiredMode.REQUIRED)
								   	String reviewDesignType,
								   	@Schema(description = "리뷰 디자인 포지션", requiredMode = Schema.RequiredMode.REQUIRED)
								   	String reviewDesignPosition,
								   	@Schema(description = "테마 이름", requiredMode = Schema.RequiredMode.REQUIRED)
								   	String themeName,
									@Schema(description = "레이아웃 타입", requiredMode = Schema.RequiredMode.REQUIRED)
									String layoutType,
									@Schema(description = "패딩", requiredMode = Schema.RequiredMode.REQUIRED)
									String padding,
									@Schema(description = "갭", requiredMode = Schema.RequiredMode.REQUIRED)
									String gap,
									@Schema(description = "박스 그림자 색상", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
									String boxShadowColor,
									@Schema(description = "박스 그림자 너비", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
									int boxShadowWidth,
									@Schema(description = "경계 색상", requiredMode = Schema.RequiredMode.REQUIRED)
									String borderColor,
									@Schema(description = "경계 투명도", requiredMode = Schema.RequiredMode.REQUIRED)
									int borderTransparency,
									@Schema(description = "경계 너비", requiredMode = Schema.RequiredMode.REQUIRED)
									int borderWidth,
									@Schema(description = "페이징 타입", requiredMode = Schema.RequiredMode.REQUIRED)
									String pagingType,
									@Schema(description = "페이징 번호", requiredMode = Schema.RequiredMode.REQUIRED)
									int pagingNumber,
									@Schema(description = "텍스트 정렬", requiredMode = Schema.RequiredMode.REQUIRED)
									String textAlign,
									@Schema(description = "포인트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
									String pointColor,
									@Schema(description = "포인트 타입", requiredMode = Schema.RequiredMode.REQUIRED)
									String pointType,
									@Schema(description = "내용 줄임", requiredMode = Schema.RequiredMode.REQUIRED)
									int lineEllipsis,
									@Schema(description = "리뷰 디자인 url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
									String reviewDesignUrl) {

}
