package com.romanticpipe.reviewcanvas.admin.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is null")
public class ShopAdmin extends BaseEntityWithUpdate implements Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_admin_id")
	private Integer id;

	private String mallId;
	private String email;
	private String password;
	private String mallName;
	private String logoImageUrl;
	private String mallNumber;
	private String phoneNumber;
	private Boolean approveStatus;
	private String businessNumber;
	private LocalDateTime deletedAt;

	@Builder
	public ShopAdmin(String mallId, String email, String password, String mallName, String logoImageUrl,
					 String mallNumber, String phoneNumber, Boolean approveStatus, String businessNumber) {
		this.mallId = mallId;
		this.email = email;
		this.password = password;
		this.mallName = mallName;
		this.logoImageUrl = logoImageUrl;
		this.mallNumber = mallNumber;
		this.phoneNumber = phoneNumber;
		this.approveStatus = approveStatus;
		this.businessNumber = businessNumber;
	}

	public boolean isApproveStatus() {
		return approveStatus;
	}

	@Override
	public AdminRole getRole() {
		return AdminRole.ROLE_SHOP_ADMIN;
	}
}
