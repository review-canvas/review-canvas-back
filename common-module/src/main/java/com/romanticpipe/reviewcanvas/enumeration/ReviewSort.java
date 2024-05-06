package com.romanticpipe.reviewcanvas.enumeration;

import java.util.Arrays;

public enum ReviewSort {
	LATEST, HIGH_SCORE, LOW_SCORE;

	public static ReviewSort of(String direction) {
		if (direction == null) {
			return LATEST;
		}
		return Arrays.stream(ReviewSort.values())
			.filter(d -> d.name().equals(direction.toUpperCase()))
			.findFirst()
			.orElseGet(() -> LATEST);
	}
}
