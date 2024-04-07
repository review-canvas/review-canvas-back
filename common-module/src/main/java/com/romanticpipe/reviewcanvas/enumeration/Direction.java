package com.romanticpipe.reviewcanvas.enumeration;

import java.util.Arrays;

public enum Direction {
	ASC, DESC, NONE;

	public static Direction of(String direction) {
		if (direction == null) {
			return NONE;
		}
		return Arrays.stream(Direction.values())
			.filter(d -> d.name().equals(direction.toUpperCase()))
			.findFirst()
			.orElseGet(() -> NONE);
	}
}
