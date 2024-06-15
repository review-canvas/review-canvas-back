package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.DetailViewType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FilterType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.PagingType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.SeeMoreButtonType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import com.romanticpipe.reviewcanvas.reviewproperty.dto.ReviewDesignViewDto;
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

import static com.romanticpipe.reviewcanvas.enumeration.Color.BLUE;
import static com.romanticpipe.reviewcanvas.enumeration.Color.GRAY;
import static com.romanticpipe.reviewcanvas.enumeration.Color.WHITE;

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
			.filterActiveTextColor(BLUE.getHex())
			.reviewBackgroundColor(WHITE.getHex())
			.margin(Margin.createDefaultReviewDesignView())
			.padding(Padding.createDefaultReviewDesignView())
			.detailInfoTextColor(GRAY.getHex())
			.font(Font.createDefaultReviewDesignView())
			.border(Border.createDefaultReviewDesignView())
			.round(Round.createDefaultReviewDesignView())
			.borderColor(WHITE.getHex())
			.shadow(Shadow.SMALL)
			.seeMoreButtonType(SeeMoreButtonType.SEE_MORE_TOGGLE)
			.replyBackgroundColor(WHITE.getHex())
			.reviewPreviewTextMaxSize(150)
			.reviewLike(ReviewLike.createDefaultReviewLike())
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(ReviewDesignViewDto dto) {
		this.detailViewType = dto.detailViewType();
		this.pagingType = dto.pagingType();
		this.filterType = dto.filterType();
		this.filterActiveTextColor = dto.filterActiveTextColor();
		this.reviewBackgroundColor = dto.reviewBackgroundColor();
		this.margin = dto.margin();
		this.padding = dto.padding();
		this.detailInfoTextColor = dto.detailInfoTextColor();
		this.font = dto.font();
		this.border = dto.border();
		this.round = dto.round();
		this.borderColor = dto.borderColor();
		this.shadow = dto.shadow();
		this.replyBackgroundColor = dto.replyBackgroundColor();
		this.reviewPreviewTextMaxSize = dto.reviewPreviewTextMaxSize();
		this.seeMoreButtonType = dto.seeMoreButtonType();
		this.reviewLike = dto.reviewLike();
	}

	public void reset() {
		this.detailViewType = DetailViewType.MODAL;
		this.pagingType = PagingType.PAGE_NUMBER;
		this.filterType = FilterType.LIST;
		this.filterActiveTextColor = BLUE.getHex();
		this.reviewBackgroundColor = WHITE.getHex();
		this.margin = Margin.createDefaultReviewDesignView();
		this.padding = Padding.createDefaultReviewDesignView();
		this.detailInfoTextColor = GRAY.getHex();
		this.font = Font.createDefaultReviewDesignView();
		this.border = Border.createDefaultReviewDesignView();
		this.round = Round.createDefaultReviewDesignView();
		this.borderColor = WHITE.getHex();
		this.shadow = Shadow.SMALL;
		this.replyBackgroundColor = WHITE.getHex();
		this.reviewPreviewTextMaxSize = 150;
		this.seeMoreButtonType = SeeMoreButtonType.SEE_MORE_TOGGLE;
		this.reviewLike = ReviewLike.createDefaultReviewLike();
	}
}
