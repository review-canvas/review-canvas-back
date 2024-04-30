package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "PostReviewContainerRequest", description = "Review Container 디자인 수정 요청")
public record UpdateContainerRequest(@Schema(description = "가로", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String width,
									 @Schema(description = "내부여백 왼쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String paddingLeft,
									 @Schema(description = "내부여백 오른쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String paddingRight,
									 @Schema(description = "내부여백 위쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String paddingTop,
									 @Schema(description = "내부여백 아래쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String paddingBottom,
									 @Schema(description = "배경", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String background,
									 @Schema(description = "테두리 왼쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String boarderLeft,
									 @Schema(description = "테두리 오른쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String boarderRight,
									 @Schema(description = "테두리 위쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String boarderTop,
									 @Schema(description = "테두리 아래쪽", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String boarderBottom,
									 @Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String boarderColor,
									 @Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String shadow
) {

}
