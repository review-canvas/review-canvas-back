package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignWrite;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewDesignWritePageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "리뷰 디자인-리뷰 쓰기 설정값")
public record GetReviewDesignWriteResponse(
	@Schema(description = "리뷰 쓰기 페이지 방식", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewDesignWritePageType pageType,
	@Schema(description = "가로 사이즈 - pc", requiredMode = Schema.RequiredMode.REQUIRED)
	String widthSizePc,
	@Schema(description = "가로 사이즈 - mobile", requiredMode = Schema.RequiredMode.REQUIRED)
	String widthSizeMobile,
	@Schema(description = "배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String backgroundColor,
	@Schema(description = "항목 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	Margin margin,
	@Schema(description = "항목 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border border,
	@Schema(description = "항목 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String borderColor,
	@Schema(description = "별점 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String starRateBackgroundColor,
	@Schema(description = "별점 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String starRateColor,
	@Schema(description = "상세 평가 항목 체크박스 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String detailEvaluationCheckBoxBackgroundColor,
	@Schema(description = "상세 평가 항목 체크 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String detailEvaluationCheckBoxColor,
	@Schema(description = "상세 평가 항목 만들기", requiredMode = Schema.RequiredMode.REQUIRED)
	String detailEvaluationCategory,
	@Schema(description = "취소 버튼 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String cancelButtonBackgroundColor,
	@Schema(description = "취소 버튼 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String cancelButtonBorderColor,
	@Schema(description = "취소 버튼 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String cancelButtonTextColor,
	@Schema(description = "작성완료 버튼 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String completedButtonBackgroundColor,
	@Schema(description = "작성완료 버튼 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String completedButtonTextColor
) {
	public static GetReviewDesignWriteResponse from(ReviewDesignWrite reviewDesignWrite) {
		return GetReviewDesignWriteResponse.builder()
			.pageType(reviewDesignWrite.getPageType())
			.widthSizePc(reviewDesignWrite.getWidthSizePc())
			.widthSizeMobile(reviewDesignWrite.getWidthSizeMobile())
			.backgroundColor(reviewDesignWrite.getBackgroundColor())
			.margin(reviewDesignWrite.getMargin())
			.border(reviewDesignWrite.getBorder())
			.borderColor(reviewDesignWrite.getBorderColor())
			.starRateBackgroundColor(reviewDesignWrite.getStarRateBackgroundColor())
			.starRateColor(reviewDesignWrite.getStarRateColor())
			.detailEvaluationCheckBoxBackgroundColor(reviewDesignWrite.getDetailEvaluationCheckBoxBackgroundColor())
			.detailEvaluationCheckBoxColor(reviewDesignWrite.getDetailEvaluationCheckBoxColor())
			.detailEvaluationCategory(reviewDesignWrite.getDetailEvaluationCategory())
			.cancelButtonBackgroundColor(reviewDesignWrite.getCancelButtonBackgroundColor())
			.cancelButtonBorderColor(reviewDesignWrite.getCancelButtonBorderColor())
			.cancelButtonTextColor(reviewDesignWrite.getCancelButtonTextColor())
			.completedButtonBackgroundColor(reviewDesignWrite.getCompletedButtonBackgroundColor())
			.completedButtonTextColor(reviewDesignWrite.getCompletedButtonTextColor())
			.build();
	}
}
