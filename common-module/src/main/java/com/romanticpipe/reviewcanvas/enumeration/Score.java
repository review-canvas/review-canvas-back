package com.romanticpipe.reviewcanvas.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Score {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	private final int value;
}
