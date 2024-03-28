package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PageableUtils {
	public static Pageable toPageable(PageableRequest pageableRequest) {
		return Pageable.ofSize(pageableRequest.size())
			.withPage(pageableRequest.page());
	}

	public static <T> PageResponse<T> toPageResponse(Page<T> page) {
		return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
	}
}
