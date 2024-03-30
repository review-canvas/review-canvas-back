package com.romanticpipe.reviewcanvas.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(c -> c.disable())
			.cors(c -> c.disable())
			.formLogin(c -> c.disable())
			.httpBasic(c -> c.disable())
			.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.headers(c -> c.frameOptions(f -> f.disable()).disable())
			.authorizeHttpRequests(auth -> {
				try {
					auth
						.requestMatchers("/").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
						.anyRequest().permitAll()
					;
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
