package com.romanticpipe.reviewcanvas.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient cafe24WebClient() {
		HttpClient httpClient = HttpClient.create()
			.wiretap("reactor.netty.http.client.HttpClient", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL)
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
			.responseTimeout(Duration.ofMillis(5_000))
			.doOnConnected(conn ->
				conn.addHandlerLast(new ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));

		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();
	}
}