package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class DesignItemSuper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "design_item_super_id")
	private Long id;

	private String themeName;
	private String layoutType;
	private String padding;
	private String gap;
	private String boxShadowColor;
	private int boxShadowWidth;
	private String borderColor;
	private int borderTransparency;
	private int borderWidth;
	private String pagingType;
	private int pagingNumber;
	private String textAlign;
	private String pointColor;
	private String pointType;
	private int lineEllipsis;

	@Builder
	public DesignItemSuper(String themeName, String layoutType, String padding, String gap, String boxShadowColor,
		int boxShadowWidth,
		String borderColor, int borderTransparency, int borderWidth, String pagingType, int pagingNumber,
		String textAlign, String pointColor,
		String pointType, int lineEllipsis) {
		this.themeName = themeName;
		this.layoutType = layoutType;
		this.padding = padding;
		this.gap = gap;
		this.boxShadowColor = boxShadowColor;
		this.boxShadowWidth = boxShadowWidth;
		this.borderColor = borderColor;
		this.borderTransparency = borderTransparency;
		this.borderWidth = borderWidth;
		this.pagingType = pagingType;
		this.pagingNumber = pagingNumber;
		this.textAlign = textAlign;
		this.pointColor = pointColor;
		this.pointType = pointType;
		this.lineEllipsis = lineEllipsis;
	}

}
