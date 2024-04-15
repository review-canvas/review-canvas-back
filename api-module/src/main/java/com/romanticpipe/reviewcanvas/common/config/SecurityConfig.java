package com.romanticpipe.reviewcanvas.common.config;

import com.romanticpipe.reviewcanvas.common.security.AuthFilter;
import com.romanticpipe.reviewcanvas.common.security.CustomAccessDeniedHandler;
import com.romanticpipe.reviewcanvas.common.security.CustomEntryPoint;
import com.romanticpipe.reviewcanvas.common.security.TokenProvider;
import com.romanticpipe.reviewcanvas.common.security.UrlList;
import com.romanticpipe.reviewcanvas.domain.Role;
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

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final CustomEntryPoint entryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;
	private final UrlList urlList;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(c -> c.configurationSource(corsConfigurationSource()))
			.csrf(c -> c.disable())
			.formLogin(c -> c.disable())
			.httpBasic(c -> c.disable())
			.headers(c -> c.frameOptions(f -> f.disable()).disable())
			.authorizeHttpRequests(auth -> {
				auth
					.requestMatchers(urlList.getPublicUrls().toArray(new String[0])).permitAll()
					.requestMatchers(urlList.getSuperUrls().toArray(new String[0])).hasAuthority(
						String.valueOf(Role.SUPER_ADMIN_ROLE))
					.anyRequest().authenticated();
			}).exceptionHandling(c ->
				c.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDeniedHandler)
			).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(new AuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
			Arrays.asList("https://api.review-canvas.com"));
		configuration.setAllowedMethods(Arrays.asList("*"));
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
