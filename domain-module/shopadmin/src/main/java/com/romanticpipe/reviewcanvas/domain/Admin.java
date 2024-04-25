package com.romanticpipe.reviewcanvas.domain;

public interface Admin {
	Integer getId();

	String getEmail();

	String getPassword();

	AdminRole getRole();
}
