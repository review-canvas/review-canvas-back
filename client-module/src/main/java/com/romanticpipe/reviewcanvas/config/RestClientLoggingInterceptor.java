package com.romanticpipe.reviewcanvas.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
		throws IOException {
		ClientHttpResponse clientHttpResponse = execution.execute(request, body);

		if (!clientHttpResponse.getStatusCode().is2xxSuccessful()) {
			replaceResponseHeaders(request, clientHttpResponse.getHeaders());
		}
		return clientHttpResponse;
	}

	private void replaceResponseHeaders(HttpRequest request, HttpHeaders responseHeaders) {
		responseHeaders.clear();
		responseHeaders.add("URL", request.getURI().toString());
		responseHeaders.add("Method", request.getMethod().toString());
		responseHeaders.addAll(request.getHeaders());
	}
}
