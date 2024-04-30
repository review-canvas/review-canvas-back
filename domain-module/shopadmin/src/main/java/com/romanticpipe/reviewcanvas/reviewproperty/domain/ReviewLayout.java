package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import jakarta.persistence.Column;
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
public class ReviewLayout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_layout_id")
	private Integer id;

	private Boolean bestReviewAreaActivation;
	private Boolean reviewStatisticsAreaActivation;
	private Boolean imageReviewAreaActivation;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private FocusAreaLayout focusAreaLayout;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ImageReviewAreaLayout imageReviewAreaLayout;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewLayoutDesign reviewLayoutDesign;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewLayout(Boolean bestReviewAreaActivation, Boolean reviewStatisticsAreaActivation,
						 Boolean imageReviewAreaActivation, FocusAreaLayout focusAreaLayout,
						 ImageReviewAreaLayout imageReviewAreaLayout, ReviewLayoutDesign reviewLayoutDesign,
						 Integer shopAdminId) {
		this.bestReviewAreaActivation = bestReviewAreaActivation;
		this.reviewStatisticsAreaActivation = reviewStatisticsAreaActivation;
		this.imageReviewAreaActivation = imageReviewAreaActivation;
		this.focusAreaLayout = focusAreaLayout;
		this.imageReviewAreaLayout = imageReviewAreaLayout;
		this.reviewLayoutDesign = reviewLayoutDesign;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewLayout create(Integer shopAdminId) {
		return ReviewLayout.builder()
			.bestReviewAreaActivation(true)
			.reviewStatisticsAreaActivation(true)
			.imageReviewAreaActivation(true)
			.focusAreaLayout(FocusAreaLayout.BEST_REVIEW_TOP)
			.imageReviewAreaLayout(ImageReviewAreaLayout.REVIEW_TOP)
			.reviewLayoutDesign(ReviewLayoutDesign.BOARD)
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(boolean bestReviewAreaActivation, boolean reviewStaticsAreaActivation,
		boolean imageReviewAreaActivation, FocusAreaLayout focusAreaLayout,
		ImageReviewAreaLayout imageReviewAreaLayout, ReviewLayoutDesign reviewLayoutDesign) {
		this.bestReviewAreaActivation = bestReviewAreaActivation;
		this.reviewStatisticsAreaActivation = reviewStaticsAreaActivation;
		this.imageReviewAreaActivation = imageReviewAreaActivation;
		this.focusAreaLayout = focusAreaLayout;
		this.imageReviewAreaLayout = imageReviewAreaLayout;
		this.reviewLayoutDesign = reviewLayoutDesign;
	}

	public void initialize() {
		this.bestReviewAreaActivation = true;
		this.reviewStatisticsAreaActivation = true;
		this.imageReviewAreaActivation = true;
		this.focusAreaLayout = FocusAreaLayout.BEST_REVIEW_TOP;
		this.imageReviewAreaLayout = ImageReviewAreaLayout.REVIEW_TOP;
		this.reviewLayoutDesign = ReviewLayoutDesign.BOARD;
	}
}
