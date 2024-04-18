package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ShopAdmin extends BaseEntityWithUpdate implements Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_admin_id")
	private Long id;

	@NotBlank
	private String mallId;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String mallName;
	private String logoImageUrl;
	private String mallNumber;
	private String phoneNumber;
	private Boolean approveStatus;
	private String businessNumber;
	private LocalDateTime deletedAt;

	private Long modalReviewDesignId;
	private Long listReviewDesignId;
	private Long adminAuthId;

	@Builder
	public ShopAdmin(String mallId, String email, String password, String mallName, String logoImageUrl,
					 String mallNumber, String phoneNumber, Boolean approveStatus, String businessNumber,
					 Long modalReviewDesignId, Long listReviewDesignId, Long adminAuthId) {
		this.mallId = mallId;
		this.email = email;
		this.password = password;
		this.mallName = mallName;
		this.logoImageUrl = logoImageUrl;
		this.mallNumber = mallNumber;
		this.phoneNumber = phoneNumber;
		this.approveStatus = approveStatus;
		this.businessNumber = businessNumber;
		this.modalReviewDesignId = modalReviewDesignId;
		this.listReviewDesignId = listReviewDesignId;
		this.adminAuthId = adminAuthId;
	}

	public boolean isApproveStatus() {
		return approveStatus;
	}

	@Override
	public AdminRole getRole() {
		return AdminRole.ROLE_SHOP_ADMIN;
	}
}
