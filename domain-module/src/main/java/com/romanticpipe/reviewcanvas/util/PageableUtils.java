package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
	public static Pageable toPageable(PageableRequest pageableRequest) {
		return PageRequest.of(pageableRequest.page(), pageableRequest.size(), getSort(pageableRequest.direction()));
	}

	public static <T> PageResponse<T> toPageResponse(Page<T> page) {
		return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
	}

	private static Sort getSort(Direction direction) {
		if (direction == Direction.LATEST) {
			return Sort.by(Sort.Direction.DESC, "createdAt");
		} else if (direction == Direction.HIGH_SCORE) {
			return Sort.by(Sort.Direction.DESC, "score");
		} else if (direction == Direction.LOW_SCORE) {
			return Sort.by(Sort.Direction.ASC, "score");
		}

		throw new IllegalArgumentException("Invalid Direction: " + direction);
	}
}
