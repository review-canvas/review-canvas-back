package com.romanticpipe.reviewcanvas.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ShopAdmin implements AdminInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_admin_id")
	private Long id;

	private String email;
	private String password;

	private UUID uuid;

	private Long adminAuthId;

	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Builder
	public ShopAdmin(String email, String password) {
		this.email = email;
		this.password = password;
		this.role = Role.USER;
		generateUuid();
	}

	public void generateUuid() { // UUID 관련 로직은 변경점이 많을 듯해 임시로 만듬.
		this.uuid = UUID.randomUUID();
	}
}
