package com.romanticpipe.reviewcanvas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
		throws IOException {
		ClientHttpResponse clientHttpResponse = execution.execute(request, body);
		if (!clientHttpResponse.getStatusCode().is2xxSuccessful()) {
			logError(request, clientHttpResponse);
		}
		return clientHttpResponse;
	}

	private void logError(HttpRequest request, ClientHttpResponse response) throws IOException {
		StringBuilder requestInfo = new StringBuilder()
			.append("[").append(request.getMethod()).append("] ").append(request.getURI()).append(" Headers: ")
			.append(request.getHeaders().entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue())
				.collect(Collectors.joining(", ")));

		StringBuilder responseInfo = new StringBuilder()
			.append(response.getStatusCode()).append(" ")
			.append(new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8));

		log.warn("[Request]: {} [Response]: {}", requestInfo, responseInfo);
	}
}
