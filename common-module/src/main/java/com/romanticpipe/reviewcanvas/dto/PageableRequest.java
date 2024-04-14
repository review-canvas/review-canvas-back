package com.romanticpipe.reviewcanvas.dto;

import com.romanticpipe.reviewcanvas.enumeration.Direction;

public record PageableRequest(int page, int size, Direction direction) {

	public PageableRequest {
		if (page < 0 || size < 1) {
			String errorMessage = String.format("Invalid PageableRequest: page=%d, size=%d,", page, size);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static PageableRequest of(int page, int size, Direction direction) {
		return new PageableRequest(page, size, direction);
	}

}
