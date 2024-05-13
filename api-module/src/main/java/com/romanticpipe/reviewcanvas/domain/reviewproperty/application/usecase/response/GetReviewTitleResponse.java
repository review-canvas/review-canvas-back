package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.AlignmentPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "GetReviewTitleResponse", description = "리뷰 제목 조회 응답")
public record GetReviewTitleResponse(
	@Schema(description = "리뷰 제목", requiredMode = Schema.RequiredMode.REQUIRED)
	String title,
	@Schema(description = "리뷰 제목 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	AlignmentPosition titleAlignmentPosition,
	@Schema(description = "리뷰 제목 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding titlePadding,
	@Schema(description = "리뷰 제목 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	Font titleFont,
	@Schema(description = "리뷰 제목 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border titleBorder,
	@Schema(description = "리뷰 제목 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String titleBorderColor,
	@Schema(description = "리뷰 제목 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String titleBackGround,
	@Schema(description = "리뷰 설명글", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String description,
	@Schema(description = "리뷰 설명글 정렬 위치", requiredMode = Schema.RequiredMode.REQUIRED)
	AlignmentPosition descriptionAlignmentPosition,
	@Schema(description = "리뷰 설명글 패딩", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding descriptionPadding,
	@Schema(description = "리뷰 설명글 폰트", requiredMode = Schema.RequiredMode.REQUIRED)
	Font descriptionFont,
	@Schema(description = "리뷰 설명글 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border descriptionBorder,
	@Schema(description = "리뷰 설명글 테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String descriptionBorderColor,
	@Schema(description = "리뷰 설명글 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	String descriptionBackGround
) {

	public static GetReviewTitleResponse from(ReviewTitle reviewTitle, ReviewTitle reviewDescription) {
		return GetReviewTitleResponse.builder()
			.title(reviewTitle.getTitleName())
			.titleAlignmentPosition(reviewTitle.getAlignmentPosition())
			.titlePadding(reviewTitle.getPadding())
			.titleFont(reviewTitle.getFont())
			.titleBorder(reviewTitle.getBorder())
			.titleBorderColor(reviewTitle.getBorderColor())
			.titleBackGround(reviewTitle.getBackground())
			.description(reviewDescription.getTitleName())
			.descriptionAlignmentPosition(reviewDescription.getAlignmentPosition())
			.descriptionPadding(reviewDescription.getPadding())
			.descriptionFont(reviewDescription.getFont())
			.descriptionBorder(reviewDescription.getBorder())
			.descriptionBorderColor(reviewDescription.getBorderColor())
			.descriptionBackGround(reviewDescription.getBackground())
			.build();
	}

}
