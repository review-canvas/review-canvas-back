package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Border {

	@Column(name = "border_left")
	private String left;
	@Column(name = "border_right")
	private String right;
	@Column(name = "border_top")
	private String top;
	@Column(name = "border_bottom")
	private String bottom;

	public Border(String left, String right, String top, String bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public static Border createDefaultReviewContainer() {
		return new Border("0px", "0px", "0px", "0px");
	}

	public static Border createDefaultReviewColumn() {
		return new Border("1px", "1px", "1px", "1px");
	}

	public static Border createDefaultReviewTitle() {
		return new Border("0px", "0px", "0px", "0px");
	}

	public static Border createDefaultReviewDescription() {
		return new Border("0px", "0px", "0px", "0px");
	}
}
