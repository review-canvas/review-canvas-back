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
public class ReviewContainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_container_id")
	private Integer id;

	private String width;
	@Embedded
	private Padding padding;
	private String background;
	@Embedded
	private Border border;
	private String borderColor;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewContainer(String width, Padding padding, String background, Border border, String borderColor,
		Shadow shadow,
		Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.border = border;
		this.borderColor = borderColor;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewContainer create(Integer shopAdminId) {
		return ReviewContainer.builder()
			.width("Full")
			.padding(Padding.createDefaultReviewContainer())
			.background("#ffffff")
			.border(Border.createDefaultReviewContainer())
			.borderColor("#ffffff")
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(String width, Padding padding, String background, Border border, String borderColor,
		Shadow shadow) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.border = border;
		this.borderColor = borderColor;
		this.shadow = shadow;
	}

	public void reset() {
		this.width = "Full";
		this.padding = Padding.createDefaultReviewContainer();
		this.background = "#ffffff";
		this.border = Border.createDefaultReviewContainer();
		this.borderColor = "#ffffff";
		this.shadow = Shadow.NONE;
	}
}
