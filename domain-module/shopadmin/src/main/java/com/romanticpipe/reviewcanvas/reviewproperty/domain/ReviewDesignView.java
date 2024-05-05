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
public class ReviewDesignView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_design_view_id")
	private Integer id;

	private String detailViewType;
	private String pagingType;
	private String filterType;
	private String filterActiveTextColor;
	private String reviewBackgroundColor;
	@Embedded
	private Margin margin;
	@Embedded
	private Padding padding;
	private String detailInfoTextColor;
	@Embedded
	private Font font;
	@Embedded
	private Border border;
	@Embedded
	private Round round;
	private String borderColor;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private String replyBackgroundColor;
	private Integer reviewPreviewTextMaxSize;
	private String seeMoreButtonType;
	@Embedded
	private ReviewLike reviewLike;
	private Integer shopAdminId;

	@Builder
	private ReviewDesignView(String detailViewType, String pagingType, String filterType, String filterActiveTextColor,
							 String reviewBackgroundColor, Margin margin, Padding padding, String detailInfoTextColor,
							 Font font, Border border, Round round, String borderColor, Shadow shadow,
							 String replyBackgroundColor, Integer reviewPreviewTextMaxSize, String seeMoreButtonType,
							 ReviewLike reviewLike, Integer shopAdminId) {
		this.detailViewType = detailViewType;
		this.pagingType = pagingType;
		this.filterType = filterType;
		this.filterActiveTextColor = filterActiveTextColor;
		this.reviewBackgroundColor = reviewBackgroundColor;
		this.margin = margin;
		this.padding = padding;
		this.detailInfoTextColor = detailInfoTextColor;
		this.font = font;
		this.border = border;
		this.round = round;
		this.borderColor = borderColor;
		this.shadow = shadow;
		this.replyBackgroundColor = replyBackgroundColor;
		this.reviewPreviewTextMaxSize = reviewPreviewTextMaxSize;
		this.seeMoreButtonType = seeMoreButtonType;
		this.reviewLike = reviewLike;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewDesignView create(Integer shopAdminId) {
		return ReviewDesignView.builder()
			.detailViewType("MODAL")
			.pagingType("PAGE_NUMBER")
			.filterType("LIST")
			.filterActiveTextColor("#3F21BD")
			.reviewBackgroundColor("#ffffff")
			.margin(Margin.createDefaultReviewDesignView())
			.padding(Padding.createDefaultReviewDesignView())
			.detailInfoTextColor("#8d8d8d")
			.font(Font.createDefaultReviewDesignView())
			.border(Border.createDefaultReviewDesignView())
			.round(Round.createDefaultReviewDesignView())
			.borderColor("#ffffff")
			.shadow(Shadow.SMALL)
			.seeMoreButtonType("FIRST")
			.replyBackgroundColor("#ffffff")
			.reviewPreviewTextMaxSize(150)
			.reviewLike(ReviewLike.createDefaultReviewLike())
			.shopAdminId(shopAdminId)
			.build();
	}
}
