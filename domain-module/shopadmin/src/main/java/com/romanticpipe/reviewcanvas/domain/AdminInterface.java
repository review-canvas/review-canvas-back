package com.romanticpipe.reviewcanvas.domain;

public interface AdminInterface {
	Long getId();

	String getEmail();

	String getPassword();

	Role getRole();

	Long getAdminAuthId();
}
