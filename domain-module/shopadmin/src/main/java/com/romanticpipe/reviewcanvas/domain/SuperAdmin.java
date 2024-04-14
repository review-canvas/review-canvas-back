package com.romanticpipe.reviewcanvas.domain;

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
public class SuperAdmin implements AdminInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "super_admin_id")
	private Long id;

	private String email;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	public SuperAdmin(String email, String password) {
		this.email = email;
		this.password = password;
		this.role = Role.SUPER_ADMIN_ROLE;
	}
}
