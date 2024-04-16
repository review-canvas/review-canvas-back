package com.romanticpipe.reviewcanvas.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.common.dto.ErrorResponse;
import com.romanticpipe.reviewcanvas.common.security.exception.TokenException;
import com.romanticpipe.reviewcanvas.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandlerFilter extends OncePerRequestFilter {

	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (TokenException e) {
			ErrorCode errorCode = e.getErrorCode();
			log.info("security exception = {} {}", errorCode, errorCode.getMessage());

			ErrorResponse errorResponse = ErrorResponse.of(errorCode);
			response.setStatus(errorCode.getStatus());
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		}
	}

}
