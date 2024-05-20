package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AdminResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(JwtInfo.class) && parameter.hasParameterAnnotation(AuthInfo.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer adminId = (Integer) authentication.getPrincipal();
		AdminRole adminRole = authentication.getAuthorities().stream()
			.findFirst()
			.map(authority -> AdminRole.valueOf(authority.getAuthority()))
			.orElseThrow(() -> new IllegalStateException("권한이 없습니다."));

		return new JwtInfo(adminId, adminRole);
	}
}
