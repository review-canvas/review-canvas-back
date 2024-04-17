package com.romanticpipe.reviewcanvas.domain;

import java.util.UUID;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class ShopAdmin extends BaseEntityWithUpdate implements AdminInterface {

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
	private UUID uuid;

	private Long adminAuthId;

	@Enumerated(EnumType.STRING)
	private Role role = Role.SHOP_ADMIN_ROLE;

	@Enumerated(EnumType.STRING)
	@Column(name = "install_type", columnDefinition = "VARCHAR(32)")
	private ShopInstallType shopInstallType;
	private String installRequirement;

	private Long selectedReviewDesignId;

	@Builder
	public ShopAdmin(ReviewVisibility reviewVisibility, String email, String password, String name, String logoImageUrl,
		String mallNumber, String phoneNumber, Boolean approveStatus, ShopInstallType shopInstallType,
		String installRequirement, Long selectedReviewDesignId, Long adminAuthId) {
		this.reviewVisibility = reviewVisibility;
		this.email = email;
		this.password = password;
		this.role = Role.SHOP_ADMIN_ROLE;
		this.name = name;
		this.logoImageUrl = logoImageUrl;
		this.mallNumber = mallNumber;
		this.phoneNumber = phoneNumber;
		this.approveStatus = approveStatus;
		this.shopInstallType = shopInstallType;
		this.installRequirement = installRequirement;
		this.selectedReviewDesignId = selectedReviewDesignId;
		this.adminAuthId = adminAuthId;
		this.uuid = UUID.randomUUID();
	}

	public boolean isApproveStatus() {
		return approveStatus;
	}
}
