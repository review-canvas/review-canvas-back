package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "베스트리뷰, 통계, 이미지/동영상, 리뷰 영역에 대한 설정값")
public record GetReviewColumnResponse(
	@Schema(description = "가로", requiredMode = Schema.RequiredMode.REQUIRED)
	String width,
	@Schema(description = "내부여백", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding padding,
	@Schema(description = "외부여백", requiredMode = Schema.RequiredMode.REQUIRED)
	Margin margin,
	@Schema(description = "배경", requiredMode = Schema.RequiredMode.REQUIRED)
	String background,
	@Schema(description = "테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border border,
	@Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String borderColor,
	@Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
	Shadow shadow
) {
	public static GetReviewColumnResponse from(ReviewColumn reviewColumn) {
		return GetReviewColumnResponse.builder()
			.width(reviewColumn.getWidth())
			.padding(reviewColumn.getPadding())
			.margin(reviewColumn.getMargin())
			.background(reviewColumn.getBackground())
			.border(reviewColumn.getBorder())
			.borderColor(reviewColumn.getBorderColor())
			.shadow(reviewColumn.getShadow())
			.build();
	}
}
