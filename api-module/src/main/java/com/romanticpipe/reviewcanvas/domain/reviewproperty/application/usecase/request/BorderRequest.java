package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record BorderRequest(
	@Schema(description = "테두리 왼쪽 두께", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String left,
	@Schema(description = "테두리 오른쪽 두께", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String right,
	@Schema(description = "테두리 상단 두께", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String top,
	@Schema(description = "테두리 하단 두께", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String bottom
) {
	public Border toVO() {
		return new Border(left, right, top, bottom);
	}
}
