package com.romanticpipe.reviewcanvas.review_property.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Margin {

	@Column(name = "margin_left")
	private String left;
	@Column(name = "margin_right")
	private String right;
	@Column(name = "margin_top")
	private String top;
	@Column(name = "margin_bottom")
	private String bottom;

	public Margin(String left, String right, String top, String bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public static Margin createDefaultReviewColumn() {
		return new Margin("10px", "10px", "10px", "10px");
	}
}
