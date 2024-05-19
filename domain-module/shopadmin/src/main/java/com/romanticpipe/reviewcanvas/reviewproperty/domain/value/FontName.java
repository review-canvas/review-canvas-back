package com.romanticpipe.reviewcanvas.reviewproperty.domain.value;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FontName {
	NONE("적용안함"),
	NOTO_SANS_KR("noto-sans-kr"),
	ROBOTO("roboto");

	private final String fontFullName;

	@JsonValue
	public String getFontFullName() {
		return fontFullName;
	}
}
