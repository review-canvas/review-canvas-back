package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.LayoutRequest;

public interface ReviewLayoutUseCase {
	void saveLayout(Integer adminId, LayoutRequest layoutRequest);
}
