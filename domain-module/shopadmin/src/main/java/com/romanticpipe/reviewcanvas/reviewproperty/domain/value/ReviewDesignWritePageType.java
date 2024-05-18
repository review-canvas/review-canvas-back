package com.romanticpipe.reviewcanvas.reviewproperty.domain.value;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewDesignWritePageType {

	NEW_PAGE("새페이지"),
	MODAL("모달(팝업)");

	private final String name;
}
