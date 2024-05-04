package com.romanticpipe.reviewcanvas.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.common.security.AccessPath;
import com.romanticpipe.reviewcanvas.common.security.AuthFilter;
import com.romanticpipe.reviewcanvas.common.security.CustomExceptionHandlerFilter;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final TokenProvider tokenProvider;
	private final AccessPath accessPath;
	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(c -> c.configurationSource(corsConfigurationSource()))
			.csrf(c -> c.disable())
			.formLogin(c -> c.disable())
			.httpBasic(c -> c.disable())
			.logout(c -> c.disable())
			.headers(c -> c.frameOptions(f -> f.disable()).disable())
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(new AuthFilter(tokenProvider, accessPath), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new CustomExceptionHandlerFilter(objectMapper), AuthFilter.class);
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
			List.of("http://localhost:3000", "http://localhost:3001", "http://localhost:3002",
				"https://shop-admin.review-canvas.com"));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L); // 1시간

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
