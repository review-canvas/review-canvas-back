package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SuperAdmin implements Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "super_admin_id")
	private Long id;

	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotNull
	private Long adminAuthId;

	@Override
	public AdminRole getRole() {
		return AdminRole.ROLE_SUPER_ADMIN;
	}
}
