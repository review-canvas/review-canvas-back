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
import lombok.Setter;

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
	private Boarder boarder;
	private String boarderColor;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewColumn(String width, Padding padding, Margin margin, String background, Boarder boarder, String boarderColor,
						 Shadow shadow, Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.margin = margin;
		this.background = background;
		this.boarder = boarder;
		this.boarderColor = boarderColor;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewColumn create(Integer shopAdminId) {
		return ReviewColumn.builder()
			.width("사이트 Width")
			.padding(Padding.createDefaultReviewColumn())
			.margin(Margin.createDefaultReviewColumn())
			.background("blue")
			.boarder(Boarder.createDefaultReviewColumn())
			.boarderColor("#ffffff")
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}

	public void reset() {
		this.width = "사이트 Width";
		this.padding = Padding.createDefaultReviewColumn();
		this.margin = Margin.createDefaultReviewColumn();
		this.background = "blue";
		this.boarder = Boarder.createDefaultReviewColumn();
		this.boarderColor = "#ffffff";
		this.shadow = Shadow.NONE;
	}

	public void update(String width, Padding padding, Margin margin, String background, Boarder boarder, String boarderColor,
		Shadow shadow) {
		this.width = width;
		this.padding = padding;
		this.margin = margin;
		this.background = background;
		this.boarder = boarder;
		this.boarderColor = boarderColor;
		this.shadow = shadow;
	}
}
