package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Shadow;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UpdateColumnRequest", description = "Column 속성 값 요청")
public record UpdateColumnRequest(
	@Schema(description = "Review Column Width", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String width,
	@Schema(description = "Review Column Padding", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Padding padding,
	@Schema(description = "Review Column Margin", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Margin margin,
	@Schema(description = "Review Column Background", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String background,
	@Schema(description = "Review Column Border", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Border border,
	@Schema(description = "Review Column Border Color", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String borderColor,
	@Schema(description = "Review Column Shadow", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Shadow shadow
) {

}
