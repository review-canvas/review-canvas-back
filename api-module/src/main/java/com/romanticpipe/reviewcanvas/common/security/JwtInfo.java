package com.romanticpipe.reviewcanvas.common.security;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;

public record JwtInfo(Integer adminId, AdminRole adminRole) {
}
