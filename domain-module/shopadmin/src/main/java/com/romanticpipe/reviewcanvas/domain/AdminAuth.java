package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class AdminAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_auth_id")
	private Long id;
	
	@Setter
	private String refreshToken;

	public static AdminAuth create() {
		return new AdminAuth();
	}

	public void updateRefreshToken(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
	}

	public void logout() {
		this.refreshToken = null;
	}
}
