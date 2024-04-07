package com.romanticpipe.reviewcanvas.cafe24;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24AccessToken(String accessToken,
								LocalDateTime expiresAt,
								String refreshToken,
								LocalDateTime refreshTokenExpiresAt,
								String clientId,
								String mallId,
								String userId,
								String[] scopes,
								LocalDateTime issuedAt) {
}
