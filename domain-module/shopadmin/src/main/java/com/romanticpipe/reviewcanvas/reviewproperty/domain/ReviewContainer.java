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
	private String boarderColor;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewContainer(String width, Padding padding, String background, Boarder boarder, String boarderColor,
		Shadow shadow,
		Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.boarder = boarder;
		this.boarderColor = boarderColor;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewContainer create(Integer shopAdminId) {
		return init(shopAdminId);
	}

	private static ReviewContainer init(Integer shopAdminId) {
		return ReviewContainer.builder()
			.width("Full")
			.padding(Padding.createDefaultReviewContainer())
			.background("#ffffff")
			.boarder(Boarder.createDefaultReviewContainer())
			.boarderColor("#ffffff")
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(String width, String paddingLeft, String paddingRight, String paddingTop, String paddingBottom,
		String background, String boarderLeft, String boarderRight, String boarderTop, String boarderBottom,
		String boarderColor, String shadow) {
		this.width = width;
		this.padding.update(paddingLeft, paddingRight, paddingTop, paddingBottom);
		this.background = background;
		this.boarder.update(boarderLeft, boarderRight, boarderTop, boarderBottom);
		this.boarderColor = boarderColor;
		this.shadow = Shadow.valueOf(shadow);
	}
}
