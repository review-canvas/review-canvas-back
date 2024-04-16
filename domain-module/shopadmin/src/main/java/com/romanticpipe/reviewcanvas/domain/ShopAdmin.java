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
public class ShopAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_admin_id")
	private Long id;

	@Column(name = "modal_review_design_id")
	private Long modalReviewDesignId;

	@Column(name = "list_review_design_id")
	private Long listReviewDesignId;

	@Column(name = "approve_status", columnDefinition = "TINYINT(1)")
	private boolean approveStatus;
}
