package com.romanticpipe.reviewcanvas.reviewproperty.dto;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewDesignWritePageType;
import lombok.Builder;

@Builder
public record ReviewDesignWriteDto(
	ReviewDesignWritePageType pageType, String widthSizePc, String widthSizeMobile, String backgroundColor,
	Margin margin, Border border, String borderColor, String starRateBackgroundColor, String starRateColor,
	String detailEvaluationCheckBoxBackgroundColor, String detailEvaluationCheckBoxColor,
	String detailEvaluationCategory, String cancelButtonBackgroundColor, String cancelButtonBorderColor,
	String cancelButtonTextColor, String completedButtonBackgroundColor, String completedButtonTextColor
) {
}
