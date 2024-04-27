package com.romanticpipe.reviewcanvas.cafe24.application;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public record Cafe24CreateScriptTagDto(
	Request request
) {

	public static Cafe24CreateScriptTagDto of(
		String src,
		List<String> displayLocation,
		List<String> excludeLocation,
		List<Integer> skinNo
	) {
		return new Cafe24CreateScriptTagDto(
			new Request(src, displayLocation, excludeLocation, skinNo)
		);
	}

	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	private record Request(
		String src,
		List<String> displayLocation,
		List<String> excludeLocation,
		List<Integer> skinNo
	) {
	}
}
