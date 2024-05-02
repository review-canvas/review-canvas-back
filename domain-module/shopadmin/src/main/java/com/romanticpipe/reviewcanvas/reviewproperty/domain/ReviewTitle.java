package com.romanticpipe.reviewcanvas.reviewproperty.domain;

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
	private Boarder boarder;
	private String boarderColor;
	private String background;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewTitle(ReviewTitleType reviewTitleType, String titleName, AlignmentPosition alignmentPosition,
		Padding padding, Font font, Boarder boarder, String boarderColor, String background, Integer shopAdminId) {
		this.reviewTitleType = reviewTitleType;
		this.titleName = titleName;
		this.alignmentPosition = alignmentPosition;
		this.padding = padding;
		this.font = font;
		this.boarder = boarder;
		this.boarderColor = boarderColor;
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
			.boarder(Boarder.createDefaultReviewTitle())
			.boarderColor("#ffffff")
			.background("#ffffff")
			.shopAdminId(shopAdminId)
			.build();
	}

	public static ReviewTitle createDescription(Integer shopAdminId) {
		return ReviewTitle.builder()
			.reviewTitleType(ReviewTitleType.DESCRIPTION)
			.titleName(null)
			.alignmentPosition(AlignmentPosition.LEFT)
			.padding(Padding.createDefaultReviewDescription())
			.font(Font.createDefaultReviewDescription())
			.boarder(Boarder.createDefaultReviewDescription())
			.boarderColor("#ffffff")
			.background("#ffffff")
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(String titleName, AlignmentPosition alignmentPosition, Padding padding, Font font,
		Boarder boarder, String boarderColor, String backGround) {
		this.titleName = titleName;
		this.alignmentPosition = alignmentPosition;
		this.padding = padding;
		this.font = font;
		this.boarder = boarder;
		this.boarderColor = boarderColor;
		this.background = backGround;
	}

	public void resetTitle() {
		this.titleName = "REVIEW";
		this.alignmentPosition = AlignmentPosition.LEFT;
		this.padding = Padding.createDefaultReviewTitle();
		this.font = Font.createDefaultReviewTitle();
		this.boarder = Boarder.createDefaultReviewTitle();
		this.boarderColor = "#ffffff";
		this.background = "#ffffff";
	}

	public void resetDescription() {
		this.titleName = null;
		this.alignmentPosition = AlignmentPosition.LEFT;
		this.padding = Padding.createDefaultReviewDescription();
		this.font = Font.createDefaultReviewDescription();
		this.boarder = Boarder.createDefaultReviewDescription();
		this.boarderColor = "#ffffff";
		this.background = "#ffffff";
	}
}
