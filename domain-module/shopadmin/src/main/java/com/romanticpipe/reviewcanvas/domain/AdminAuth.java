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
public class AdminAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_auth_id")
	private Long id;

	private String refreshToken;

	private Integer shopAdminId;
	private Integer superAdminId;

	public AdminAuth(String refreshToken, Integer shopAdminId, Integer superAdminId) {
		this.refreshToken = refreshToken;
		this.shopAdminId = shopAdminId;
		this.superAdminId = superAdminId;
	}

	public static AdminAuth createShopAdminAuth(Integer shopAdminId) {
		return new AdminAuth(null, shopAdminId, null);
	}

	public void updateRefreshToken(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
	}

	public void logout() {
		this.refreshToken = null;
	}
}
