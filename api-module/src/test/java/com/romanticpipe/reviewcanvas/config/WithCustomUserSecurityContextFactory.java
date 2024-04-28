package com.romanticpipe.reviewcanvas.config;

import com.romanticpipe.reviewcanvas.common.security.JwtAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collection;
import java.util.List;

public class WithCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomAdmin> {

	@Override
	public SecurityContext createSecurityContext(WithCustomAdmin customAdmin) {
		Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customAdmin.role()));
		JwtAuthenticationToken authentication = new JwtAuthenticationToken(customAdmin.adminId(), authorities);
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		return context;
	}

}
