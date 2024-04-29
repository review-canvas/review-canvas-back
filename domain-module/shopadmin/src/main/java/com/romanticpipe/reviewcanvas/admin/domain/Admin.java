package com.romanticpipe.reviewcanvas.admin.domain;

public interface Admin {
	Integer getId();

	String getEmail();

	String getPassword();

	AdminRole getRole();
}
