package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontBold;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FontRequest(
	@Schema(description = "폰트 이름(구글폰트)", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull FontName name,
	@Schema(description = "폰트 사이즈", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String size,
	@Schema(description = "폰트 두께", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull FontBold bold,
	@Schema(description = "폰트 색상", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String color
) {
	public Font toVO() {
		return new Font(name, size, bold, color);
	}
}
