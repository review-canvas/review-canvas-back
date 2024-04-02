package com.romanticpipe.reviewcanvas.common.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final AdminInterface admin;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(admin.getRole().toString());
		return Collections.singleton(new SimpleGrantedAuthority(admin.getRole().toString()));
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.admin.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getShopAdminId() {
		return this.admin.getId();
	}

	public ShopAdmin getShopAdmin() {
		return (ShopAdmin)this.admin;
	}
}
