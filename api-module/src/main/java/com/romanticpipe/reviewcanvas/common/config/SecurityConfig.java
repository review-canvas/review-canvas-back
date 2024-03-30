package com.romanticpipe.reviewcanvas.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.romanticpipe.reviewcanvas.common.security.CustomAccessDeniedHandler;
import com.romanticpipe.reviewcanvas.common.security.CustomEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	private final CustomEntryPoint entryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;

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
						.requestMatchers("/",
							"/api/v1/shopadmin/login",
							"/api/v1/shopadmin/signup").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
						.anyRequest().permitAll()
					;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).exceptionHandling(c ->
				c.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDeniedHandler)
			);
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
