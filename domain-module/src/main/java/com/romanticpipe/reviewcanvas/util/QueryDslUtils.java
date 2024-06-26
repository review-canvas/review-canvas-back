package com.romanticpipe.reviewcanvas.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryDslUtils {

	public static <T> List<OrderSpecifier<String>> getOrderSpecifiers(Sort sort, EntityPathBase<T> targetEntity) {
		List<OrderSpecifier<String>> orderSpecifiers = new ArrayList<>();
		sort.stream().forEach(order -> {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			PathBuilder<T> pathBuilder = new PathBuilder<>(targetEntity.getType(), targetEntity.getMetadata());
			orderSpecifiers.add(new OrderSpecifier<>(direction, pathBuilder.getString(order.getProperty())));
		});
		return orderSpecifiers;
	}
}
