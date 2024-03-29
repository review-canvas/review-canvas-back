package com.romaintcpipe.reviewcanvas.domain;

import java.util.UUID;

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
	private String id;

	private String email;
	private String passward;

	private UUID uuid;

	public void generateUuid() { // UUID 관련 로직은 변경점이 많을 듯해 임시로 만듬.
		this.uuid = UUID.randomUUID();
	}
}
