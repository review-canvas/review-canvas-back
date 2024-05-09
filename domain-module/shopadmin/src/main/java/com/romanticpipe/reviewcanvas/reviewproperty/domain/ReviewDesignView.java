package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.DetailViewType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FilterType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.PagingType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.SeeMoreButtonType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
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

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private DetailViewType detailViewType;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private PagingType pagingType;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private FilterType filterType;
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
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private SeeMoreButtonType seeMoreButtonType;
	@Embedded
	private ReviewLike reviewLike;
	private Integer shopAdminId;

	@Builder
	private ReviewDesignView(DetailViewType detailViewType, PagingType pagingType, FilterType filterType,
							 String filterActiveTextColor,
							 String reviewBackgroundColor, Margin margin, Padding padding, String detailInfoTextColor,
							 Font font, Border border, Round round, String borderColor, Shadow shadow,
							 String replyBackgroundColor, Integer reviewPreviewTextMaxSize,
							 SeeMoreButtonType seeMoreButtonType, ReviewLike reviewLike, Integer shopAdminId) {
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
			.detailViewType(DetailViewType.MODAL)
			.pagingType(PagingType.PAGE_NUMBER)
			.filterType(FilterType.LIST)
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
			.seeMoreButtonType(SeeMoreButtonType.SEE_MORE_TOGGLE)
			.replyBackgroundColor("#ffffff")
			.reviewPreviewTextMaxSize(150)
			.reviewLike(ReviewLike.createDefaultReviewLike())
			.shopAdminId(shopAdminId)
			.build();
	}
}
