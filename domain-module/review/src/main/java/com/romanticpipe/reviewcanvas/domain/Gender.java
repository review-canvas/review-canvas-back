package com.romanticpipe.reviewcanvas.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Gender {
	MALE("M"), FEMALE("F");

	@Getter
	private final String value;

	public static Gender of(String gender) {
		return Arrays.stream(Gender.values())
			.filter(g -> g.value.equals(gender))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("성별이 올바르지 않습니다."));
	}
}
