package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.domain.AdminRole;

public record JwtInfo(Integer adminId, AdminRole adminRole) {
}
