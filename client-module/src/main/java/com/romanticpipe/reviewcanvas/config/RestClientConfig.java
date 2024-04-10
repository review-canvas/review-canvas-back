package com.romanticpipe.reviewcanvas.config;

import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;

import java.time.Duration;

@Configuration
class RestClientConfig {

	@Bean
	public ClientHttpRequestFactory defaultClientHttpRequestFactory() {
		ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
			.withConnectTimeout(Duration.ofMinutes(5L))
			.withReadTimeout(Duration.ofMinutes(5L));

		return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
	}
}
