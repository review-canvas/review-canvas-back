package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;

public interface ReviewLayoutUseCase {
	void updateLayout(Integer adminId, UpdateLayoutRequest updateLayoutRequest);
}
