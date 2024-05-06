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

	@Column(name = "round_top_left")
	private String topLeft;
	@Column(name = "round_top_right")
	private String topRight;
	@Column(name = "round_bottom_left")
	private String bottomLeft;
	@Column(name = "round_bottom_right")
	private String bottomRight;

	public Round(String topLeft, String topRight, String bottomLeft, String bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}

	public static Round createDefaultReviewDesignView() {
		return new Round("0", "0", "0", "0");
	}
}
