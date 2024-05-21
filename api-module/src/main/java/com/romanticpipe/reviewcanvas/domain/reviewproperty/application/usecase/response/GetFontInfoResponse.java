package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontBold;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GetFontInfoResponse(
	@Schema(description = "폰트 이름 목록", requiredMode = Schema.RequiredMode.REQUIRED)
	List<FontName> fontNames,
	@Schema(description = "폰트 두께 목록", requiredMode = Schema.RequiredMode.REQUIRED)
	List<FontBold> fontBolds
) {
}
