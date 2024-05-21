package com.romanticpipe.reviewcanvas.reviewproperty.domain.value;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FontBold {

	ONE_HUNDRED("100"),
	TWO_HUNDRED("200"),
	THREE_HUNDRED("300"),
	FOUR_HUNDRED("400"),
	FIVE_HUNDRED("500"),
	SIX_HUNDRED("600"),
	SEVEN_HUNDRED("700"),
	BOLD("bold");

	private final String name;

	@JsonValue
	public String getName() {
		return name;
	}

}
