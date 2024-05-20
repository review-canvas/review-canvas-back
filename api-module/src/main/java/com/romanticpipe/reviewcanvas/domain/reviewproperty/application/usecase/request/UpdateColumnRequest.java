package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UpdateColumnRequest", description = "Column 속성 값 요청")
public record UpdateColumnRequest(
	@Schema(description = "Review Column Width", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull String width,
	@Schema(description = "Review Column Padding", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid PaddingRequest padding,
	@Schema(description = "Review Column Margin", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid MarginRequest margin,
	@Schema(description = "Review Column Background", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	@NotBlank String background,
	@Schema(description = "Review Column Border", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid BorderRequest border,
	@Schema(description = "Review Column Border Color", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	@NotBlank String borderColor,
	@Schema(description = "Review Column Shadow", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Shadow shadow
) {

}
