package com.romanticpipe.reviewcanvas.enumeration;

import java.util.Arrays;

public enum Direction {
	LATEST, HIGH_SCORE, LOW_SCORE;

	public static Direction of(String direction) {
		if (direction == null) {
			return LATEST;
		}
		return Arrays.stream(Direction.values())
			.filter(d -> d.name().equals(direction.toUpperCase()))
			.findFirst()
			.orElseGet(() -> LATEST);
	}
}
