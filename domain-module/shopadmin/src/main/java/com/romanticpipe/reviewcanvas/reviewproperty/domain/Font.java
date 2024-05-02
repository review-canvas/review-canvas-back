package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Font {

	@Column(name = "font_name")
	private String name;
	@Column(name = "font_size")
	private String size;
	@Column(name = "font_bold")
	private String bold;
	@Column(name = "font_color")
	private String color;

	public Font(String name, String size, String bold, String color) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.color = color;
	}

	public static Font createDefaultReviewTitle() {
		return new Font("noto-sans-kr", "16px", "400", "#000000");
	}

	public static Font createDefaultReviewDescription() {
		return new Font("noto-sans-kr", "12px", "400", "#000000");
	}
}
