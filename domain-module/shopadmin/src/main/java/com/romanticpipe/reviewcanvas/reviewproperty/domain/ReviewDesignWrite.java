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

import static com.romanticpipe.reviewcanvas.enumeration.Color.BLACK;
import static com.romanticpipe.reviewcanvas.enumeration.Color.BLUE;
import static com.romanticpipe.reviewcanvas.enumeration.Color.LIGHT_BLACK;
import static com.romanticpipe.reviewcanvas.enumeration.Color.WHITE;

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
	private Padding padding;
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
							 String backgroundColor, Padding padding, Border border, String borderColor,
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
		this.padding = padding;
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
			.backgroundColor(BLACK.getHex())
			.padding(Padding.createDefaultReviewDesignWrite())
			.border(Border.createDefaultReviewDesignWrite())
			.borderColor(LIGHT_BLACK.getHex())
			.starRateBackgroundColor(BLACK.getHex())
			.starRateColor(WHITE.getHex())
			.detailEvaluationCheckBoxBackgroundColor(BLACK.getHex())
			.detailEvaluationCheckBoxColor(BLUE.getHex())
			.detailEvaluationCategory("")
			.cancelButtonBackgroundColor(LIGHT_BLACK.getHex())
			.cancelButtonBorderColor(BLACK.getHex())
			.cancelButtonTextColor(BLACK.getHex())
			.completedButtonBackgroundColor(BLUE.getHex())
			.completedButtonTextColor(BLACK.getHex())
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(ReviewDesignWriteDto dto) {
		this.pageType = dto.pageType();
		this.widthSizePc = dto.widthSizePc();
		this.widthSizeMobile = dto.widthSizeMobile();
		this.backgroundColor = dto.backgroundColor();
		this.padding = dto.padding();
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
		this.backgroundColor = BLACK.getHex();
		this.padding = Padding.createDefaultReviewDesignWrite();
		this.border = Border.createDefaultReviewDesignWrite();
		this.borderColor = LIGHT_BLACK.getHex();
		this.starRateBackgroundColor = BLACK.getHex();
		this.starRateColor = WHITE.getHex();
		this.detailEvaluationCheckBoxBackgroundColor = BLACK.getHex();
		this.detailEvaluationCheckBoxColor = BLUE.getHex();
		this.detailEvaluationCategory = "";
		this.cancelButtonBackgroundColor = LIGHT_BLACK.getHex();
		this.cancelButtonBorderColor = BLACK.getHex();
		this.cancelButtonTextColor = BLACK.getHex();
		this.completedButtonBackgroundColor = BLUE.getHex();
		this.completedButtonTextColor = BLACK.getHex();
	}
}
