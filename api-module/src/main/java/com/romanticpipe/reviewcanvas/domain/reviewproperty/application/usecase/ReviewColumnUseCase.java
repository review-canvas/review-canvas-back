package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

public interface ReviewColumnUseCase {

	ReviewColumn getColumnByShopAdminId(Integer shopAdminId);

	void updateReviewColumn(Integer shopAdminId, UpdateColumnRequest updateColumnRequest);

	void resetReviewColumn(Integer shopAdminId);
}
