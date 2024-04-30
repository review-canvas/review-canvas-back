package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetReviewContainerResponse", description = "리뷰 Container 설정 값을 반환하는 Response")
public record GetReviewContainerResponse(@Schema(description = "가로", requiredMode = Schema.RequiredMode.REQUIRED)
										 String width,
										 @Schema(description = "내부여백 왼쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String paddingLeft,
										 @Schema(description = "내부여백 오른쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String paddingRight,
										 @Schema(description = "내부여백 위쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String paddingTop,
										 @Schema(description = "내부여백 아래쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String paddingBottom,
										 @Schema(description = "배경", requiredMode = Schema.RequiredMode.REQUIRED)
										 String background,
										 @Schema(description = "테두리 왼쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String boarderLeft,
										 @Schema(description = "테두리 오른쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String boarderRight,
										 @Schema(description = "테두리 위쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String boarderTop,
										 @Schema(description = "테두리 아래쪽", requiredMode = Schema.RequiredMode.REQUIRED)
										 String boarderBottom,
										 @Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
										 String boarderColor,
										 @Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
										 String shadow

) {

	public static GetReviewContainerResponse from(ReviewContainer reviewContainer) {
		return new GetReviewContainerResponse(
			reviewContainer.getWidth(),
			reviewContainer.getPadding().getLeft(),
			reviewContainer.getPadding().getRight(),
			reviewContainer.getPadding().getTop(),
			reviewContainer.getPadding().getBottom(),
			reviewContainer.getBackground(),
			reviewContainer.getBoarder().getLeft(),
			reviewContainer.getBoarder().getRight(),
			reviewContainer.getBoarder().getTop(),
			reviewContainer.getBoarder().getBottom(),
			reviewContainer.getBoarderColor(),
			reviewContainer.getShadow().name()
		);
	}
}
