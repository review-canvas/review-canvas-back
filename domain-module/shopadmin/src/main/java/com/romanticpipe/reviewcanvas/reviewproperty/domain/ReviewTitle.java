package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.AlignmentPosition;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewTitleType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_title_id")
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewTitleType reviewTitleType;
	private String titleName;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private AlignmentPosition alignmentPosition;
	@Embedded
	private Padding padding;
	@Embedded
	private Font font;
	@Embedded
	private Border border;
	private String borderColor;
	private String background;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewTitle(ReviewTitleType reviewTitleType, String titleName, AlignmentPosition alignmentPosition,
						Padding padding, Font font, Border border, String borderColor, String background,
						Integer shopAdminId) {
		this.reviewTitleType = reviewTitleType;
		this.titleName = titleName;
		this.alignmentPosition = alignmentPosition;
		this.padding = padding;
		this.font = font;
		this.border = border;
		this.borderColor = borderColor;
		this.background = background;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewTitle createTitle(Integer shopAdminId) {
		return ReviewTitle.builder()
			.reviewTitleType(ReviewTitleType.TITLE)
			.titleName("REVIEW")
			.alignmentPosition(AlignmentPosition.LEFT)
			.padding(Padding.createDefaultReviewTitle())
			.font(Font.createDefaultReviewTitle())
			.border(Border.createDefaultReviewTitle())
			.borderColor("#ffffff")
			.background("#ffffff")
			.shopAdminId(shopAdminId)
			.build();
	}

	public static ReviewTitle createDescription(Integer shopAdminId) {
		return ReviewTitle.builder()
			.reviewTitleType(ReviewTitleType.DESCRIPTION)
			.titleName("")
			.alignmentPosition(AlignmentPosition.LEFT)
			.padding(Padding.createDefaultReviewDescription())
			.font(Font.createDefaultReviewDescription())
			.border(Border.createDefaultReviewDescription())
			.borderColor("#ffffff")
			.background("#ffffff")
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(String titleName, AlignmentPosition alignmentPosition, Padding padding, Font font,
					   Border border, String borderColor, String backGround) {
		this.titleName = titleName;
		this.alignmentPosition = alignmentPosition;
		this.padding = padding;
		this.font = font;
		this.border = border;
		this.borderColor = borderColor;
		this.background = backGround;
	}

	public void initializeTitle() {
		this.titleName = "REVIEW";
		this.alignmentPosition = AlignmentPosition.LEFT;
		this.padding = Padding.createDefaultReviewTitle();
		this.font = Font.createDefaultReviewTitle();
		this.border = Border.createDefaultReviewTitle();
		this.borderColor = "#ffffff";
		this.background = "#ffffff";
	}

	public void initializeDescription() {
		this.titleName = "";
		this.alignmentPosition = AlignmentPosition.LEFT;
		this.padding = Padding.createDefaultReviewDescription();
		this.font = Font.createDefaultReviewDescription();
		this.border = Border.createDefaultReviewDescription();
		this.borderColor = "#ffffff";
		this.background = "#ffffff";
	}
}
