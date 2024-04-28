package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.cafe24.application.Cafe24ApplicationClient;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductClient;
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
	public Cafe24AuthenticationClient cafe24AuthenticationClient(
		ClientHttpRequestFactory clientHttpRequestFactory, RestClientLoggingInterceptor restClientLoggingInterceptor) {
		RestClient restClient = RestClient.builder()
			.requestFactory(clientHttpRequestFactory)
			.defaultHeader("Authorization", "Basic " + AUTHORIZATION_CODE)
			.requestInterceptor(restClientLoggingInterceptor)
			.build();

		return getHttpExchange(restClient, Cafe24AuthenticationClient.class);
	}

	@Bean
	public Cafe24ProductClient cafe24ProductClient(
		ClientHttpRequestFactory clientHttpRequestFactory, RestClientLoggingInterceptor restClientLoggingInterceptor,
		Cafe24TokenInterceptor cafe24TokenInterceptor) {
		RestClient restClient = createRestClient(clientHttpRequestFactory, restClientLoggingInterceptor,
			cafe24TokenInterceptor);

		return getHttpExchange(restClient, Cafe24ProductClient.class);
	}

	@Bean
	public Cafe24ApplicationClient cafe24ApplicationClient(
		ClientHttpRequestFactory clientHttpRequestFactory, RestClientLoggingInterceptor restClientLoggingInterceptor,
		Cafe24TokenInterceptor cafe24TokenInterceptor) {
		RestClient restClient = createRestClient(clientHttpRequestFactory, restClientLoggingInterceptor,
			cafe24TokenInterceptor);

		return getHttpExchange(restClient, Cafe24ApplicationClient.class);
	}

	private RestClient createRestClient(ClientHttpRequestFactory clientHttpRequestFactory,
										RestClientLoggingInterceptor restClientLoggingInterceptor,
										Cafe24TokenInterceptor cafe24TokenInterceptor) {
		return RestClient.builder()
			.requestFactory(clientHttpRequestFactory)
			.requestInterceptor(restClientLoggingInterceptor)
			.requestInterceptor(cafe24TokenInterceptor)
			.build();
	}
}
