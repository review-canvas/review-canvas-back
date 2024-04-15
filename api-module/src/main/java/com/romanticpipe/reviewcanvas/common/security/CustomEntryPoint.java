package com.romanticpipe.reviewcanvas.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.common.dto.ErrorResponse;
import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		writer.write(objectMapper.writeValueAsString(
			ErrorResponse.of((ErrorCode)request.getAttribute("exception"))));
	}
}
