package com.romanticpipe.reviewcanvas.common.security;

import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

	public static String getLoggedInAdminEmail() {
		try {
			return
				((AdminInterface)Objects
					.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
					.getPrincipal())
					.getEmail();
		} catch (NullPointerException e) {
			throw new BusinessException(SecurtyErrorCode.ILLEGAL_TOKEN);
		}
	}

	public static Long getLoggedInAdminId() {
		try {
			return
				((AdminInterface)Objects
					.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
					.getPrincipal())
					.getId();
		} catch (NullPointerException e) {
			throw new BusinessException(SecurtyErrorCode.ILLEGAL_TOKEN);
		}
	}
}
