package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReviewSort;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SortUtils {

	public static Sort getSort(List<Enum<?>> sortValues) {
		if (sortValues == null || sortValues.isEmpty()) {
			return Sort.unsorted();
		}
		return sortValues.stream()
			.map(sortValue -> {
				if (sortValue instanceof ReviewSort) {
					return switch ((ReviewSort) sortValue) {
						case HIGH_SCORE -> Sort.by(Sort.Order.desc(Review.Fields.score));
						case LOW_SCORE -> Sort.by(Sort.Order.asc(Review.Fields.score));
						default -> Sort.by(Sort.Order.desc("createdAt"));
					};
				} else {
					throw new IllegalArgumentException("지원하지 않는 정렬 방식입니다: " + sortValue);
				}
			})
			.reduce(Sort::and)
			.orElse(Sort.unsorted());
	}

}
