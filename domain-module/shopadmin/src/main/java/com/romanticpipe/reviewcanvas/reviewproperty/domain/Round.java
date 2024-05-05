package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Round {

	@Column(name = "round_left")
	private String left;
	@Column(name = "round_right")
	private String right;
	@Column(name = "round_top")
	private String top;
	@Column(name = "round_bottom")
	private String bottom;

	public Round(String left, String right, String top, String bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public static Round createDefaultReviewDesignView() {
		return new Round("0", "0", "0", "0");
	}
}
