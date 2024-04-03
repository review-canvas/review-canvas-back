package com.romanticpipe.reviewcanvas.domain;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
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

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "review_visibility_id")
	private ReviewVisibility reviewVisibility;

	private String email;
	private String password;
	private String name;
	private String logoImageUrl;
	private String mallNumber;
	private String phoneNumber;
	private Boolean approveStatus;
	@Column(insertable = false, updatable = false)
	private Date createdAt;
	@Column(insertable = false)
	private Date updatedAt;
	private UUID uuid;

	private String refreshToken;

	private Long selectedReviewDesignId;
	private Long myReviewDesignId;

	@Builder
	public ShopAdmin(ReviewVisibility reviewVisibility, String email, String password, String name, String logoImageUrl,
		String mallNumber,
		String phoneNumber, Boolean approveStatus, Long selectedReviewDesignId, Long myReviewDesignId) {
		this.reviewVisibility = reviewVisibility;
		this.email = email;
		this.password = password;
		this.name = name;
		this.logoImageUrl = logoImageUrl;
		this.mallNumber = mallNumber;
		this.phoneNumber = phoneNumber;
		this.approveStatus = approveStatus;
		this.selectedReviewDesignId = selectedReviewDesignId;
		this.myReviewDesignId = myReviewDesignId;
		generateUuid();
	}

	public void generateUuid() { // UUID 관련 로직은 변경점이 많을 듯해 임시로 만듬.
		this.uuid = UUID.randomUUID();
	}
}