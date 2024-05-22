package com.romanticpipe.reviewcanvas.enumeration;

import java.util.Arrays;

public enum ReviewFilterForUser {
	ALL, IMAGE_VIDEO, GENERAL;

	public static ReviewFilterForUser of(String reviewFilter) {
		if (reviewFilter == null) {
			return ALL;
		}
		return Arrays.stream(ReviewFilterForUser.values())
			.filter(r -> r.name().equals(reviewFilter.toUpperCase()))
			.findFirst()
			.orElseGet(() -> ALL);
	}
}
