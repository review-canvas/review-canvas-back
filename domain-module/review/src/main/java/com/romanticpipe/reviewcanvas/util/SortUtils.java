package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SortUtils {

	public static Sort getSort(List<Enum<?>> sorts) {
		Sort appendedSort = Sort.unsorted();
		sorts.forEach(sort -> appendSort(appendedSort, sort));
		return appendedSort;
	}

	private static void appendSort(Sort appendSort, Enum<?> sort) {
		if (sort instanceof ReviewSort) {
			if (sort == ReviewSort.LATEST) {
				appendSort.and(Sort.by(Sort.Order.desc("createdAt")));
			} else if (sort == ReviewSort.HIGH_SCORE) {
				appendSort.and(Sort.by(Sort.Order.desc(Review.Fields.score)));
			} else if (sort == ReviewSort.LOW_SCORE) {
				appendSort.and(Sort.by(Sort.Order.asc(Review.Fields.score)));
			}
		} else {
			throw new IllegalArgumentException("Invalid Direction: " + sort);
		}
	}
}
