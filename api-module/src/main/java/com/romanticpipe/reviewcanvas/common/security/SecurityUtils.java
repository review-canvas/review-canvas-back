package com.romanticpipe.reviewcanvas.common.security;

import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

	public static ShopAdmin getLoggedInShopAdmin() {
		try {
			return
				((CustomUserDetails)Objects
					.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
					.getPrincipal())
					.getShopAdmin();
		} catch (NullPointerException e) {
			throw new BusinessException(JwtErrorCode.ILLEGAL_TOKEN);
		}
	}

	public static Long getLoggedInShopAdminId() {
		try {
			return
				((CustomUserDetails)Objects
					.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
					.getPrincipal())
					.getShopAdminId();
		} catch (NullPointerException e) {
			throw new BusinessException(JwtErrorCode.ILLEGAL_TOKEN);
		}
	}
}
