package com.romanticpipe.reviewcanvas.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.romanticpipe.reviewcanvas.domain.QUser;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilter;
import com.romanticpipe.reviewcanvas.util.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.romanticpipe.reviewcanvas.domain.QReply.reply;
import static com.romanticpipe.reviewcanvas.domain.QReview.review;
import static com.romanticpipe.reviewcanvas.domain.QUser.user;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Review> findAllReview(Long productId, Pageable pageable, ReviewFilter filter) {
		List<OrderSpecifier<String>> orderSpecifiers = QueryDslUtils.getOrderSpecifiers(pageable.getSort(), review);

		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.productId.eq(productId), getFilterExpression(filter))
			.orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.join(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, new QUser("replyUser"))
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.productId.eq(productId), getFilterExpression(filter));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	private BooleanExpression getFilterExpression(ReviewFilter filter) {
		if (filter == ReviewFilter.IMAGE_VIDEO) {
			return review.imageVideoUrls.isNull();
		} else if (filter == ReviewFilter.GENERAL) {
			return review.imageVideoUrls.isNotNull();
		}
		return null;
	}
}
