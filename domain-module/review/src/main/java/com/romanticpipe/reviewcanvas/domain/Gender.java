package com.romanticpipe.reviewcanvas.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
	M("남자"), F("여자");

	@Getter
	private final String name;
}
