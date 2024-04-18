package com.romanticpipe.reviewcanvas.cafe24.authentication;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24AccessToken(String accessToken,
								LocalDateTime expiresAt,
								String refreshToken,
								LocalDateTime refreshTokenExpiresAt,
								String clientId,
								String mallId,
								String userId,
								List<String> scopes,
								LocalDateTime issuedAt) {
}
