package com.romanticpipe.reviewcanvas.admin.domain;

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
public class SuperAdmin implements Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "super_admin_id")
	private Integer id;

	private String email;
	private String password;

	@Override
	public AdminRole getRole() {
		return AdminRole.ROLE_SUPER_ADMIN;
	}
}
