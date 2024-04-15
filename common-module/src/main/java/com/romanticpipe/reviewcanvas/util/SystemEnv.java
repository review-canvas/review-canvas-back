package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.exception.EnvironmentVariableNotFoundException;

import java.util.Optional;

public class SystemEnv {

	public static String get(String key) {
		return Optional.ofNullable(System.getenv(key))
			.orElseThrow(() -> new EnvironmentVariableNotFoundException(key));
	}
}
