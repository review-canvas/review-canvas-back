package com.romanticpipe.reviewcanvas.common.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	private final Long adminId;

	public JwtAuthenticationToken(Long adminId, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.adminId = adminId;
	}

	@Override
	public Object getCredentials() {
		return adminId;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
}
