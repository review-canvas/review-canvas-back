package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike {

	@Column(name = "review_like_button_type")
	private String buttonType;
	@Column(name = "review_like_icon_color")
	private String iconColor;
	@Column(name = "review_like_text_color")
	private String textColor;
	@Column(name = "review_like_button_border_color")
	private String buttonBorderColor;
	@Column(name = "review_like_button_round_top_left")
	private String buttonRoundTopLeft;
	@Column(name = "review_like_button_round_top_right")
	private String buttonRoundTopRight;
	@Column(name = "review_like_button_round_bottom_left")
	private String buttonRoundBottomLeft;
	@Column(name = "review_like_button_round_bottom_right")
	private String buttonRoundBottomRight;

	@Builder
	public ReviewLike(String buttonType, String iconColor, String textColor,
					  String buttonBorderColor, String buttonRoundTopLeft, String buttonRoundTopRight,
					  String buttonRoundBottomLeft, String buttonRoundBottomRight) {
		this.buttonType = buttonType;
		this.iconColor = iconColor;
		this.textColor = textColor;
		this.buttonBorderColor = buttonBorderColor;
		this.buttonRoundTopLeft = buttonRoundTopLeft;
		this.buttonRoundTopRight = buttonRoundTopRight;
		this.buttonRoundBottomLeft = buttonRoundBottomLeft;
		this.buttonRoundBottomRight = buttonRoundBottomRight;
	}

	public static ReviewLike createDefaultReviewLike() {
		return ReviewLike.builder()
			.buttonType("NONE")
			.iconColor("#ffffff")
			.textColor("#3F21DB")
			.buttonBorderColor("#3F21DB")
			.buttonRoundTopLeft("5px")
			.buttonRoundTopRight("5px")
			.buttonRoundBottomLeft("5px")
			.buttonRoundBottomRight("5px")
			.build();
	}
}
