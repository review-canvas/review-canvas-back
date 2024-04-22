package com.romanticpipe.reviewcanvas.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomUserSecurityContextFactory.class)
public @interface WithCustomAdmin {

	int adminId() default 1;

	String role() default "ROLE_SHOP_ADMIN";
}
