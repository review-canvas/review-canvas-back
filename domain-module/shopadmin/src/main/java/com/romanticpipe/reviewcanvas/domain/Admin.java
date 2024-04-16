package com.romanticpipe.reviewcanvas.domain;

public interface Admin {
	Long getId();

	String getEmail();

	String getPassword();

	AdminRole getRole();

	Long getAdminAuthId();
}
