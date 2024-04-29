package com.romanticpipe.reviewcanvas.review_property.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Boarder {

	@Column(name = "boarder_left")
	private String left;
	@Column(name = "boarder_right")
	private String right;
	@Column(name = "boarder_top")
	private String top;
	@Column(name = "boarder_bottom")
	private String bottom;

	public Boarder(String left, String right, String top, String bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public static Boarder createDefaultReviewContainer() {
		return new Boarder("1px", "1px", "1px", "1px");
	}

	public static Boarder createDefaultReviewColumn() {
		return new Boarder("1px", "1px", "1px", "1px");
	}

	public static Boarder createDefaultReviewTitle() {
		return new Boarder("1px", "1px", "1px", "1px");
	}
}
