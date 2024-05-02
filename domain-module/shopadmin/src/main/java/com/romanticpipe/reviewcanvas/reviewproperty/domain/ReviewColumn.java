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
public class ReviewColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_column_id")
	private Integer id;

	private String width;
	@Embedded
	private Padding padding;
	@Embedded
	private Margin margin;
	private String background;
	@Embedded
	private Border border;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewColumn(String width, Padding padding, Margin margin, String background, Border border,
		Shadow shadow, Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.margin = margin;
		this.background = background;
		this.border = border;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewColumn create(Integer shopAdminId) {
		return ReviewColumn.builder()
			.width("사이트 Width")
			.padding(Padding.createDefaultReviewColumn())
			.margin(Margin.createDefaultReviewColumn())
			.background("blue")
			.border(Border.createDefaultReviewColumn())
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}

}
