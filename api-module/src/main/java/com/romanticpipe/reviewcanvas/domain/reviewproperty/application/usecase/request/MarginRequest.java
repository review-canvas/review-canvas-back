package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MarginRequest(
	@Schema(description = "좌측 바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String left,
	@Schema(description = "우측 바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String right,
	@Schema(description = "상단 바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String top,
	@Schema(description = "하단 바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String bottom
) {
	public Margin toVO() {
		return new Margin(left, right, top, bottom);
	}
}
