package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
	public static Pageable toPageable(PageableRequest pageableRequest, Sort sort) {
		return PageRequest.of(pageableRequest.page(), pageableRequest.size(), sort);
	}

	public static Pageable toPageable(PageableRequest pageableRequest) {
		return PageRequest.of(pageableRequest.page(), pageableRequest.size());
	}

	public static <T> PageResponse<T> toPageResponse(Page<T> page) {
		return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
	}
}
