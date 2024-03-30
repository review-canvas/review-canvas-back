package com.romanticpipe.reviewcanvas.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		List<SecurityErrorDto> errors = new ArrayList<>();
		errors.add(SecurityErrorDto.builder()
			.point("UNAUTHORIZED")
			.detail("unauthorized token")
			.build());

		ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), "UNAUTHORIZED");
		pb.setType(URI.create("/docs.html"));
		pb.setProperty("errors", errors);
		pb.setInstance(URI.create(request.getRequestURI()));
		ObjectMapper objectMapper = new ObjectMapper();

		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		writer.write(objectMapper.writeValueAsString(pb));
	}
}
