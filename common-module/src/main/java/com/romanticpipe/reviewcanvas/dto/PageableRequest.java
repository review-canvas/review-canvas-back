package com.romanticpipe.reviewcanvas.dto;

import java.util.List;

public record PageableRequest(int page, int size, List<Enum<?>> sort) {

	public PageableRequest {
		if (page < 0 || size < 1) {
			String errorMessage = String.format("Invalid PageableRequest: page=%d, size=%d,", page, size);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static PageableRequest of(int page, int size, Enum<?>... sort) {
		return new PageableRequest(page, size, List.of(sort));
	}

}
