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
	@Column(name = "review_like_button_round_left")
	private String buttonRoundLeft;
	@Column(name = "review_like_button_round_right")
	private String buttonRoundRight;
	@Column(name = "review_like_button_round_top")
	private String buttonRoundTop;
	@Column(name = "review_like_button_round_bottom")
	private String buttonRoundBottom;

	@Builder
	private ReviewLike(String buttonType, String iconColor, String textColor, String buttonBorderColor,
					   String buttonRoundLeft, String buttonRoundRight, String buttonRoundTop,
					   String buttonRoundBottom) {
		this.buttonType = buttonType;
		this.iconColor = iconColor;
		this.textColor = textColor;
		this.buttonBorderColor = buttonBorderColor;
		this.buttonRoundLeft = buttonRoundLeft;
		this.buttonRoundRight = buttonRoundRight;
		this.buttonRoundTop = buttonRoundTop;
		this.buttonRoundBottom = buttonRoundBottom;
	}

	public static ReviewLike createDefaultReviewLike() {
		return ReviewLike.builder()
			.buttonType("NONE")
			.iconColor("#ffffff")
			.textColor("#3F21DB")
			.buttonBorderColor("#3F21DB")
			.buttonRoundLeft("5px")
			.buttonRoundRight("5px")
			.buttonRoundTop("5px")
			.buttonRoundBottom("5px")
			.build();
	}
}
