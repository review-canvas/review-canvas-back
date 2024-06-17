package com.romanticpipe.reviewcanvas.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Color {
	WHITE("#ffffff"),
	BLACK("#000000"),
	GRAY("#8d8d8d"),
	BLUE("#3F21BD"),
	LIGHT_BLACK("222222");
	@Getter
	private final String hex;
}
