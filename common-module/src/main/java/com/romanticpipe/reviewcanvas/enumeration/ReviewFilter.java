package com.romanticpipe.reviewcanvas.enumeration;

import java.util.Arrays;

public enum ReviewFilter {
	ALL, IMAGE_VIDEO, GENERAL;

	public static ReviewFilter of(String reviewFilter) {
		if (reviewFilter == null) {
			return ALL;
		}
		return Arrays.stream(ReviewFilter.values())
			.filter(r -> r.name().equals(reviewFilter.toUpperCase()))
			.findFirst()
			.orElseGet(() -> ALL);
	}
}
