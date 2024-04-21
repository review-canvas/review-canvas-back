package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ShopAuthToken extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_auth_token_id")
	private Integer id;

	private String mallId;
	private String accessToken;
	private LocalDateTime accessTokenExpiresAt;
	private String refreshToken;
	private LocalDateTime refreshTokenExpiresAt;
	private String scope;

	@Builder
	public ShopAuthToken(String mallId, String accessToken, LocalDateTime accessTokenExpiresAt, String refreshToken,
						 LocalDateTime refreshTokenExpiresAt, String scope) {
		this.mallId = mallId;
		this.accessToken = accessToken;
		this.accessTokenExpiresAt = accessTokenExpiresAt;
		this.refreshToken = refreshToken;
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
		this.scope = scope;
	}
}
