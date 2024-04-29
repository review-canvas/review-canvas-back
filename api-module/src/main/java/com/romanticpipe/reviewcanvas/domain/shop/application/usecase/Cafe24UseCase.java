package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.request.Cafe24CreateScriptTagRequest;

public interface Cafe24UseCase {
	void cafe24AuthenticationProcess(String mallId, String authCode);

	String cafe24CreateScriptTag(String mallId, Cafe24CreateScriptTagRequest request);
}
