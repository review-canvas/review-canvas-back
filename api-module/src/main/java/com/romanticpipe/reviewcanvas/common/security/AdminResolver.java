package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.domain.AdminRole;
import io.jsonwebtoken.Claims;
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
		Claims claims = (Claims) authentication.getPrincipal();
		Long userId = Long.parseLong((String) claims.get(Claims.SUBJECT));
		AdminRole adminRole = AdminRole.valueOf((String) claims.get(CustomClaims.ROLE));

		return new JwtInfo(userId, adminRole);
	}
}
