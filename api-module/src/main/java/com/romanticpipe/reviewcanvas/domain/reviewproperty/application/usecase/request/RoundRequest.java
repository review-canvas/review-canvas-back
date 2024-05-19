package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Round;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RoundRequest(
	@Schema(description = "라운드 상단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String topLeft,
	@Schema(description = "라운드 상단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String topRight,
	@Schema(description = "라운드 하단 좌측", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String bottomLeft,
	@Schema(description = "라운드 하단 우측", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String bottomRight
) {
	public Round toVO() {
		return new Round(topLeft, topRight, bottomLeft, bottomRight);
	}
}
