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

	public static ReviewTitle create(Integer shopAdminId) {
		return ReviewTitle.builder()
			.reviewTitleType(ReviewTitleType.TITLE)
			.titleName("리뷰 타이틀")
			.alignmentPosition(AlignmentPosition.CENTER)
			.padding(Padding.createDefaultReviewTitle())
			.font(Font.createDefaultReviewTitle())
			.boarder(Boarder.createDefaultReviewTitle())
			.boarderColor("#ffffff")
			.background("blue")
			.shopAdminId(shopAdminId)
			.build();
	}
}
