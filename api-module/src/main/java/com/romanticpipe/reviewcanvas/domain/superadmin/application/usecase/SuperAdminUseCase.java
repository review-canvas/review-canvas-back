package com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase;

import com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface SuperAdminUseCase {
	PageResponse<GetShopInfoResponse> getShopInfos(PageableRequest pageable);
}
