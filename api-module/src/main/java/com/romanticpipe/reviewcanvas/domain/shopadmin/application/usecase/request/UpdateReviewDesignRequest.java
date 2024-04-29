package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request;

import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "ReviewDesignRequest", description = "Review Design 수정 요청")
public record UpdateReviewDesignRequest(
	@Schema(description = "리뷰 디자인 포지션", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull ReviewDesignPosition reviewDesignPosition,
	@Schema(description = "리뷰 디자인 테마 이름", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String themeName,
	@Schema(description = "레이아웃 타입", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String layoutType,
	@Schema(description = "패딩 퍼센티지", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String padding,
	@Schema(description = "아이템 간의 Gap", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String gap,
	@Schema(description = "Shadow의 색상", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String boxShadowColor,
	@Schema(description = "Shadow의 너비", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer boxShadowWidth,
	@Schema(description = "Border의 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String borderColor,
	@Schema(description = "Border의 투명도", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Integer borderTransparency,
	@Schema(description = "Border의 너비", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Integer borderWidth,
	@Schema(description = "Paging 시 표현 및 처리 방식", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String pagingType,
	@Schema(description = "Paging 기준 갯수", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Integer pagingNumber,
	@Schema(description = "텍스트 정렬 방법", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String textAlign,
	@Schema(description = "리뷰 점수의 Color", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String pointColor,
	@Schema(description = "리뷰 점수 표현 모양", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String pointType,
	@Schema(description = "Review List에서 Content 출력 줄 수", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Integer lineEllipsis,
	@Schema(description = "리뷰 디자인 이미지 url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String reviewDesignUrl
) {

}
