package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FocusAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ImageReviewAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewLayoutDesign;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "LayoutRequest", description = "Layout 속성 값 요청")
public record UpdateLayoutRequest(
	@Schema(description = "Best Review Area Activation", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Boolean bestReviewAreaActivation,
	@Schema(description = "Review Statics Activation", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Boolean reviewStatisticsAreaActivation,
	@Schema(description = "Image Review Area Activation", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Boolean imageReviewAreaActivation,
	@Schema(description = "Focus Area Layout", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid FocusAreaLayout focusAreaLayout,
	@Schema(description = "Image Review Area Layout", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid ImageReviewAreaLayout imageReviewAreaLayout,
	@Schema(description = "Review Layout Design", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid ReviewLayoutDesign reviewLayoutDesign
) {

}
