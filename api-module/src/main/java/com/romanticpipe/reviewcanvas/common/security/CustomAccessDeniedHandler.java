package com.romanticpipe.reviewcanvas.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private final ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException {
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		writer.write(objectMapper.writeValueAsString(ErrorResponse.of(SecurityErrorCode.UNAUTHORIZED)));
	}
}
