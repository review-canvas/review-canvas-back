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
public class MyReviewDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "my_review_design_id")
	private Integer id;
	private Integer reviewListDesignId;
	private Integer reviewModalDesignId;
	private Integer shopAdminId;

	@Builder
	public MyReviewDesign(Integer reviewListDesignId, Integer reviewModalDesignId, Integer shopAdminId) {
		this.reviewListDesignId = reviewListDesignId;
		this.reviewModalDesignId = reviewModalDesignId;
		this.shopAdminId = shopAdminId;
	}
}
