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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
			handleException(response, e.getErrorCode());
		}
	}

	private void handleException(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		ErrorResponse errorResponse = ErrorResponse.of(errorCode);
		response.setStatus(errorCode.getStatus());
		response.setCharacterEncoding("UTF-8");
		response.setContentType(APPLICATION_JSON_VALUE);
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}

}
