package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Font {

	@Column(name = "font_name")
	private String name;
	@Column(name = "font_size")
	private String size;
	@Column(name = "font_bold")
	private String bold;
	// @Column(name = "font_weight")
	// private String weight;
	@Column(name = "font_color")
	private String color;

	public Font(String name, String size, String bold, String weight, String color) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		// this.weight = weight;
		this.color = color;
	}

	public static Font createDefaultReviewTitle() {
		return new Font("Noto Sans KR", "12px", "normal", "normal", "#000000");
	}
}
