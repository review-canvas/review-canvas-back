package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ReviewDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_design_id")
	private Long id;

	private String reviewDesignType;
	private String reviewDesignPosition;
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
	private String reviewDesignUrl;

}
