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
	private Boarder boarder;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewContainer(String width, Padding padding, String background, Boarder boarder, Shadow shadow,
							Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.boarder = boarder;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewContainer create(Integer shopAdminId) {
		return ReviewContainer.builder()
			.width("사이트 Width")
			.padding(Padding.createDefaultReviewContainer())
			.background("blue")
			.boarder(Boarder.createDefaultReviewContainer())
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}
}
