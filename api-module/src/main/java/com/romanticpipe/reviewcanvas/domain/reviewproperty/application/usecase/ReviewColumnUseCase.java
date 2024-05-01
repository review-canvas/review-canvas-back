package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

public interface ReviewColumnUseCase {

	ReviewColumn getColumnByShopAdminId(Integer shopAdminId);
}
