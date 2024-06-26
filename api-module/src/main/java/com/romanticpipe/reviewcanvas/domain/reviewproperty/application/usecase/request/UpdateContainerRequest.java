package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UpdateReviewContainerRequest", description = "Review Container 디자인 수정 요청")
public record UpdateContainerRequest(@Schema(description = "가로", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String width,
									 @Schema(description = "내부여백", requiredMode = Schema.RequiredMode.REQUIRED)
									 @Valid PaddingRequest padding,
									 @Schema(description = "배경", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String background,
									 @Schema(description = "테두리", requiredMode = Schema.RequiredMode.REQUIRED)
									 @Valid BorderRequest border,
									 @Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotBlank String borderColor,
									 @Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
									 @NotNull Shadow shadow
) {

}
