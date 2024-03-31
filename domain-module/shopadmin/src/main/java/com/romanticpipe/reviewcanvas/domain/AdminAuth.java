package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class AdminAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_auth_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "shop_admin_id")
	private ShopAdmin shopAdmin;

	private String refreshToken;

	@Builder
	public AdminAuth(String refreshToken, ShopAdmin shopAdmin) {
		this.refreshToken = refreshToken;
		this.shopAdmin = shopAdmin;
	}
}