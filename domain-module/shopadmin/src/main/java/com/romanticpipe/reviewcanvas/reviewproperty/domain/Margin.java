package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
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
		return new Margin("0px", "0px", "15px", "0px");
	}

	public static Margin createDefaultReviewDesignView() {
		return new Margin("10px", "10px", "10px", "10px");
	}
}
