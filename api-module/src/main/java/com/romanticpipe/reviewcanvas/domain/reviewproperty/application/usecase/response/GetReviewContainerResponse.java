package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(name = "GetReviewContainerResponse", description = "리뷰 Container 설정 값을 반환하는 Response")
public record GetReviewContainerResponse(@Schema(description = "가로", requiredMode = Schema.RequiredMode.REQUIRED)
										 String width,
										 @Schema(description = "내부여백", requiredMode = Schema.RequiredMode.REQUIRED)
										 Padding padding,
										 @Schema(description = "배경", requiredMode = Schema.RequiredMode.REQUIRED)
										 String background,
										 @Schema(description = "테두리", requiredMode = Schema.RequiredMode.REQUIRED)
										 Border border,
										 @Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
										 String borderColor,
										 @Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
										 String shadow

) {

	public static GetReviewContainerResponse from(ReviewContainer reviewContainer) {
		return GetReviewContainerResponse.builder()
			.width(reviewContainer.getWidth())
			.padding(reviewContainer.getPadding())
			.background(reviewContainer.getBackground())
			.border(reviewContainer.getBorder())
			.borderColor(reviewContainer.getBorderColor())
			.shadow(reviewContainer.getShadow().name())
			.build();
	}
}
