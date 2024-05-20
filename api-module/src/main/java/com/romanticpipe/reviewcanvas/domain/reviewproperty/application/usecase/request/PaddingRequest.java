package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PaddingRequest(
	@Schema(description = "좌측 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String left,
	@Schema(description = "우측 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String right,
	@Schema(description = "상단 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String top,
	@Schema(description = "하단 안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String bottom
) {
	public Padding toVO() {
		return new Padding(left, right, top, bottom);
	}
}
