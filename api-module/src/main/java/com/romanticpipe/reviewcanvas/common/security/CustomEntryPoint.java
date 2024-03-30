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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		List<SecurityErrorDto> errors = new ArrayList<>();
		errors.add(
			SecurityErrorDto.builder()
				.point("ACCESS TOKEN / REFRESH TOKEN")
				.detail("please check request token")
				.build());

		ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), "FORBIDDEN");
		pb.setType(URI.create("/docs.html"));
		pb.setProperty("errors", errors);
		pb.setInstance(URI.create(request.getRequestURI()));
		ObjectMapper objectMapper = new ObjectMapper();

		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		writer.write(objectMapper.writeValueAsString(pb));
	}
}
