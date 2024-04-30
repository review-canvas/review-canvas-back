package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Padding {

	@Column(name = "padding_left")
	private String left;
	@Column(name = "padding_right")
	private String right;
	@Column(name = "padding_top")
	private String top;
	@Column(name = "padding_bottom")
	private String bottom;

	public Padding(String left, String right, String top, String bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public static Padding createDefaultReviewContainer() {
		return new Padding("10px", "10px", "10px", "10px");
	}

	public static Padding createDefaultReviewColumn() {
		return new Padding("10px", "10px", "10px", "10px");
	}

	public static Padding createDefaultReviewTitle() {
		return new Padding("10px", "10px", "10px", "10px");
	}
}