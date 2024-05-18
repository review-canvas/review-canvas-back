package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewDesignWritePageType;
import com.romanticpipe.reviewcanvas.reviewproperty.dto.ReviewDesignWriteDto;
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
public class ReviewDesignWrite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_design_write_id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR", name = "review_design_write_page_type")
	private ReviewDesignWritePageType pageType;
	private String widthSizePc;
	private String widthSizeMobile;
	private String backgroundColor;
	@Embedded
	private Margin margin;
	@Embedded
	private Border border;
	private String borderColor;
	private String starRateBackgroundColor;
	private String starRateColor;
	private String detailEvaluationCheckBoxBackgroundColor;
	private String detailEvaluationCheckBoxColor;
	private String detailEvaluationCategory;
	private String cancelButtonBackgroundColor;
	private String cancelButtonBorderColor;
	private String cancelButtonTextColor;
	private String completedButtonBackgroundColor;
	private String completedButtonTextColor;
	private Integer shopAdminId;

	@Builder
	public ReviewDesignWrite(ReviewDesignWritePageType pageType, String widthSizePc, String widthSizeMobile,
							 String backgroundColor, Margin margin, Border border, String borderColor,
							 String starRateBackgroundColor, String starRateColor,
							 String detailEvaluationCheckBoxBackgroundColor, String detailEvaluationCheckBoxColor,
							 String detailEvaluationCategory, String cancelButtonBackgroundColor,
							 String cancelButtonBorderColor, String cancelButtonTextColor,
							 String completedButtonBackgroundColor, String completedButtonTextColor,
							 Integer shopAdminId) {
		this.pageType = pageType;
		this.widthSizePc = widthSizePc;
		this.widthSizeMobile = widthSizeMobile;
		this.backgroundColor = backgroundColor;
		this.margin = margin;
		this.border = border;
		this.borderColor = borderColor;
		this.starRateBackgroundColor = starRateBackgroundColor;
		this.starRateColor = starRateColor;
		this.detailEvaluationCheckBoxBackgroundColor = detailEvaluationCheckBoxBackgroundColor;
		this.detailEvaluationCheckBoxColor = detailEvaluationCheckBoxColor;
		this.detailEvaluationCategory = detailEvaluationCategory;
		this.cancelButtonBackgroundColor = cancelButtonBackgroundColor;
		this.cancelButtonBorderColor = cancelButtonBorderColor;
		this.cancelButtonTextColor = cancelButtonTextColor;
		this.completedButtonBackgroundColor = completedButtonBackgroundColor;
		this.completedButtonTextColor = completedButtonTextColor;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewDesignWrite create(Integer shopAdminId) {
		return ReviewDesignWrite.builder()
			.pageType(ReviewDesignWritePageType.MODAL)
			.widthSizePc("500px")
			.widthSizeMobile("100%")
			.backgroundColor("#ffffff")
			.margin(Margin.createDefaultReviewDesignWrite())
			.border(Border.createDefaultReviewDesignWrite())
			.borderColor("#222222")
			.starRateBackgroundColor("#ffffff")
			.starRateColor("#000000")
			.detailEvaluationCheckBoxBackgroundColor("#ffffff")
			.detailEvaluationCheckBoxColor("#3F21BD")
			.detailEvaluationCategory("")
			.cancelButtonBackgroundColor("#222222")
			.cancelButtonBorderColor("#ffffff")
			.cancelButtonTextColor("#ffffff")
			.completedButtonBackgroundColor("#3F21BD")
			.completedButtonTextColor("#ffffff")
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(ReviewDesignWriteDto dto) {
		this.pageType = dto.pageType();
		this.widthSizePc = dto.widthSizePc();
		this.widthSizeMobile = dto.widthSizeMobile();
		this.backgroundColor = dto.backgroundColor();
		this.margin = dto.margin();
		this.border = dto.border();
		this.borderColor = dto.borderColor();
		this.starRateBackgroundColor = dto.starRateBackgroundColor();
		this.starRateColor = dto.starRateColor();
		this.detailEvaluationCheckBoxBackgroundColor = dto.detailEvaluationCheckBoxBackgroundColor();
		this.detailEvaluationCheckBoxColor = dto.detailEvaluationCheckBoxColor();
		this.detailEvaluationCategory = dto.detailEvaluationCategory();
		this.cancelButtonBackgroundColor = dto.cancelButtonBackgroundColor();
		this.cancelButtonBorderColor = dto.cancelButtonBorderColor();
		this.cancelButtonTextColor = dto.cancelButtonTextColor();
		this.completedButtonBackgroundColor = dto.completedButtonBackgroundColor();
		this.completedButtonTextColor = dto.completedButtonTextColor();
	}

	public void reset() {
		this.pageType = ReviewDesignWritePageType.MODAL;
		this.widthSizePc = "500px";
		this.widthSizeMobile = "100%";
		this.backgroundColor = "#ffffff";
		this.margin = Margin.createDefaultReviewDesignWrite();
		this.border = Border.createDefaultReviewDesignWrite();
		this.borderColor = "#222222";
		this.starRateBackgroundColor = "#ffffff";
		this.starRateColor = "#000000";
		this.detailEvaluationCheckBoxBackgroundColor = "#ffffff";
		this.detailEvaluationCheckBoxColor = "#3F21BD";
		this.detailEvaluationCategory = "";
		this.cancelButtonBackgroundColor = "#222222";
		this.cancelButtonBorderColor = "#ffffff";
		this.cancelButtonTextColor = "#ffffff";
		this.completedButtonBackgroundColor = "#3F21BD";
		this.completedButtonTextColor = "#ffffff";
	}
}
