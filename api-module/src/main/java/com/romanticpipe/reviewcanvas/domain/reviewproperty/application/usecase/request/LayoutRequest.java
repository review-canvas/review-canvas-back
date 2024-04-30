package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.FocusAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ImageReviewAreaLayout;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayoutDesign;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LayoutRequest", description = "Layout 속성 값 요청")
public record LayoutRequest(@Schema(description = "Best Review Area Activation", requiredMode = Schema.RequiredMode.REQUIRED)
							boolean bestReviewAreaActivation,
							@Schema(description = "Review Statics Activation", requiredMode = Schema.RequiredMode.REQUIRED)
							boolean reviewStaticsAreaActivation,
							@Schema(description = "Image Review Area Activation", requiredMode = Schema.RequiredMode.REQUIRED)
							boolean imageReviewAreaActivation,
							@Schema(description = "Focus Area Layout", requiredMode = Schema.RequiredMode.REQUIRED)
							FocusAreaLayout focusAreaLayout,
							@Schema(description = "Image Review Area Layout", requiredMode = Schema.RequiredMode.REQUIRED)
							ImageReviewAreaLayout imageReviewAreaLayout,
							@Schema(description = "Review Layout Design", requiredMode = Schema.RequiredMode.REQUIRED)
							ReviewLayoutDesign reviewLayoutDesign
							) {

}
