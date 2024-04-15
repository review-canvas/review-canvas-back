package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MyReviewDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "my_review_design_id")
	private Long id;

	private Long shopAdminId;
	private Long reviewDesignId;
}
