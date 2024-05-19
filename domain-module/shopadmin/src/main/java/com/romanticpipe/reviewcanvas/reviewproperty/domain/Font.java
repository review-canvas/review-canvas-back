package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontBold;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FontName;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Font {

	@Column(name = "font_name", columnDefinition = "VARCHAR")
	@Enumerated(EnumType.STRING)
	private FontName name;
	@Column(name = "font_size")
	private String size;
	@Column(name = "font_bold", columnDefinition = "VARCHAR")
	@Enumerated(EnumType.STRING)
	private FontBold bold;
	@Column(name = "font_color")
	private String color;

	public Font(FontName name, String size, FontBold bold, String color) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.color = color;
	}

	public static Font createDefaultReviewTitle() {
		return new Font(FontName.NOTO_SANS_KR, "16px", FontBold.FOUR_HUNDRED, "#000000");
	}

	public static Font createDefaultReviewDescription() {
		return new Font(FontName.NOTO_SANS_KR, "12px", FontBold.FOUR_HUNDRED, "#000000");
	}

	public static Font createDefaultReviewDesignView() {
		return new Font(FontName.NOTO_SANS_KR, "12px", FontBold.FOUR_HUNDRED, "#000000");
	}
}
