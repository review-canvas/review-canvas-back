package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

public interface ReviewLayoutUseCase {
	void updateLayout(Integer adminId, UpdateLayoutRequest updateLayoutRequest);

	ReviewColumn getColumn(Integer shopAdminId);
}
