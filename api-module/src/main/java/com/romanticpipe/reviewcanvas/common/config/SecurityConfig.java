package com.romanticpipe.reviewcanvas.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.romanticpipe.reviewcanvas.common.security.AuthFilter;
import com.romanticpipe.reviewcanvas.common.security.CustomAccessDeniedHandler;
import com.romanticpipe.reviewcanvas.common.security.CustomEntryPoint;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final CustomEntryPoint entryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(c -> c.disable())
			.cors(c -> c.disable())
			.formLogin(c -> c.disable())
			.httpBasic(c -> c.disable())
			.headers(c -> c.frameOptions(f -> f.disable()).disable())
			.authorizeHttpRequests(auth -> {
				auth
					.requestMatchers("/",
						"/api/v1/shopadmin/login",
						"/api/v1/shopadmin/signup").permitAll()
					.requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
					.anyRequest().authenticated();
			}).exceptionHandling(c ->
				c.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDeniedHandler)
			).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(new AuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
