package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MyReviewDesign {

	@Id
	@Column(name = "my_review_design_id")
	private Long id;

	private long reviewDesignId;
	private long shopAdminId;
}
