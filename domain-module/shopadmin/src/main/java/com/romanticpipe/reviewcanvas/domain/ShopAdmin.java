package com.romanticpipe.reviewcanvas.domain;

import java.util.UUID;

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
public class ShopAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_admin_id")
	private Long id;

	private String email;
	private String password;
	private String name;
	private String logoImageUrl;
	private String mallNumber;
	private String phoneNumber;

	private UUID uuid;

	private String accessToken;
	private String refreshToken;

	private Long themeSuperId;

	@Builder
	public ShopAdmin(String email, String password, String name, String logoImageUrl, String mallNumber,
		String phoneNumber, Long themeSuperId) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.logoImageUrl = logoImageUrl;
		this.mallNumber = mallNumber;
		this.phoneNumber = phoneNumber;
		this.themeSuperId = themeSuperId;
		generateUuid();
	}

	public void generateUuid() { // UUID 관련 로직은 변경점이 많을 듯해 임시로 만듬.
		this.uuid = UUID.randomUUID();
	}
}
