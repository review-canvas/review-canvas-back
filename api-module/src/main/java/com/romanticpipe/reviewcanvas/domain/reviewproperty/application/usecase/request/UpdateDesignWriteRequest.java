package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewDesignWritePageType;
import com.romanticpipe.reviewcanvas.reviewproperty.dto.ReviewDesignWriteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDesignWriteRequest(
	@Schema(description = "리뷰 쓰기 페이지 방식", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull ReviewDesignWritePageType pageType,
	@Schema(description = "가로 사이즈 - pc", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String widthSizePc,
	@Schema(description = "가로 사이즈 - mobile", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String widthSizeMobile,
	@Schema(description = "배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String backgroundColor,
	@Schema(description = "항목 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Padding padding,
	@Schema(description = "항목 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Border border,
	@Schema(description = "항목 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String borderColor,
	@Schema(description = "별점 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String starRateBackgroundColor,
	@Schema(description = "별점 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String starRateColor,
	@Schema(description = "상세 평가 항목 체크박스 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String detailEvaluationCheckBoxBackgroundColor,
	@Schema(description = "상세 평가 항목 체크 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String detailEvaluationCheckBoxColor,
	@Schema(description = "상세 평가 항목 만들기(빈 string값 가능)", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String detailEvaluationCategory,
	@Schema(description = "취소 버튼 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String cancelButtonBackgroundColor,
	@Schema(description = "취소 버튼 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String cancelButtonBorderColor,
	@Schema(description = "취소 버튼 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String cancelButtonTextColor,
	@Schema(description = "작성완료 버튼 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String completedButtonBackgroundColor,
	@Schema(description = "작성완료 버튼 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String completedButtonTextColor
) {


	public ReviewDesignWriteDto toDto() {
		return ReviewDesignWriteDto.builder()
			.pageType(pageType)
			.widthSizePc(widthSizePc)
			.widthSizeMobile(widthSizeMobile)
			.backgroundColor(backgroundColor)
			.padding(padding)
			.border(border)
			.borderColor(borderColor)
			.starRateBackgroundColor(starRateBackgroundColor)
			.starRateColor(starRateColor)
			.detailEvaluationCheckBoxBackgroundColor(detailEvaluationCheckBoxBackgroundColor)
			.detailEvaluationCheckBoxColor(detailEvaluationCheckBoxColor)
			.detailEvaluationCategory(detailEvaluationCategory)
			.cancelButtonBackgroundColor(cancelButtonBackgroundColor)
			.cancelButtonBorderColor(cancelButtonBorderColor)
			.cancelButtonTextColor(cancelButtonTextColor)
			.completedButtonBackgroundColor(completedButtonBackgroundColor)
			.completedButtonTextColor(completedButtonTextColor)
			.build();
	}
}
