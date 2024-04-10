package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.config.RestClientLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static com.romanticpipe.reviewcanvas.cafe24.Cafe24Properties.AUTHORIZATION_CODE;

@Configuration
class Cafe24HttpExchangeConfig {

	public static <T> T getHttpExchange(RestClient restClient, Class<T> clazz) {
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(clazz);
	}

	@Bean
	public Cafe24AuthenticationClient cafe24AuthenticationClient(ClientHttpRequestFactory clientHttpRequestFactory,
																 RestClientLoggingInterceptor restClientLoggingInterceptor) {
		RestClient restClient = RestClient.builder()
			.requestFactory(clientHttpRequestFactory)
			.defaultHeader("Authorization", "Basic " + AUTHORIZATION_CODE)
			.requestInterceptor(restClientLoggingInterceptor)
			.build();

		return getHttpExchange(restClient, Cafe24AuthenticationClient.class);
	}
}
