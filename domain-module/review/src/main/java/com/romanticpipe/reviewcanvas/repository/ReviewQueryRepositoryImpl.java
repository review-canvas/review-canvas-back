package com.romanticpipe.reviewcanvas.repository;

import static com.romanticpipe.reviewcanvas.domain.QReply.*;
import static com.romanticpipe.reviewcanvas.domain.QReview.*;
import static com.romanticpipe.reviewcanvas.domain.QUser.*;
import static com.romanticpipe.reviewcanvas.util.FileExtensionUtils.*;

import java.util.EnumSet;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.romanticpipe.reviewcanvas.domain.QUser;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import com.romanticpipe.reviewcanvas.util.QueryDslUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Review> findAllReview(Long productId, Pageable pageable, ReviewFilterForUser filter) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.productId.eq(productId), getFilterExpression(filter))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.leftJoin(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, new QUser("replyUser"))
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.productId.eq(productId), getFilterExpression(filter));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<Review> findByProductId(Long productId, Pageable pageable,
		EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.productId.eq(productId), getReviewTypeCondition(reviewFilters), getScoreCondition(score),
				getReplyExistCondition(replyFilters))
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
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.productId.eq(productId), getReviewTypeCondition(reviewFilters), getScoreCondition(score),
				getReplyExistCondition(replyFilters));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	private OrderSpecifier<?>[] getReviewOrderSpecifiers(Pageable pageable) {
		return QueryDslUtils.getOrderSpecifiers(pageable.getSort(), review).toArray(OrderSpecifier[]::new);
	}

	private BooleanExpression getFilterExpression(ReviewFilterForUser filter) {
		if (filter == ReviewFilterForUser.IMAGE_VIDEO) {
			return review.imageVideoUrls.isNull();
		} else if (filter == ReviewFilterForUser.GENERAL) {
			return review.imageVideoUrls.isNotNull();
		}
		return null;
	}

	private BooleanExpression getReviewTypeCondition(EnumSet<ReviewFilterForShopAdmin> filters) {
		if (filters.size() == 1) {
			if (filters.contains(ReviewFilterForShopAdmin.PHOTO)) {
				return getExtensionsExpression(IMAGE_EXTENSIONS);
			} else if (filters.contains(ReviewFilterForShopAdmin.VIDEO)) {
				return getExtensionsExpression(VIDEO_EXTENSIONS);
			} else if (filters.contains(ReviewFilterForShopAdmin.TEXT)) {
				return review.imageVideoUrls.isNull();
			}
		} else if (filters.size() == 2) {
			if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.PHOTO, ReviewFilterForShopAdmin.VIDEO))) {
				return review.imageVideoUrls.isNotNull();
			} else if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.PHOTO, ReviewFilterForShopAdmin.TEXT))) {
				return review.imageVideoUrls.isNull().or(getExtensionsExpression(IMAGE_EXTENSIONS));
			} else if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.VIDEO, ReviewFilterForShopAdmin.TEXT))) {
				return review.imageVideoUrls.isNull().or(getExtensionsExpression(VIDEO_EXTENSIONS));
			}
		}
		return null;
	}

	private BooleanExpression getScoreCondition(EnumSet<Score> score) {
		List<Integer> scoreList = score.stream().map(Score::getValue).toList();
		return review.score.in(scoreList);
	}

	private BooleanExpression getReplyExistCondition(EnumSet<ReplyFilter> replyFilters) {
		if (replyFilters.size() == 1) {
			if (replyFilters.contains(ReplyFilter.REPLIED)) {
				return review.replyList.isNotEmpty();
			} else if (replyFilters.contains(ReplyFilter.NOT_REPLIED)) {
				return review.replyList.isEmpty();
			}
		}
		return null;
	}

	private BooleanExpression getExtensionsExpression(List<String> imageExtensions) {
		return imageExtensions.stream()
			.map(ext -> review.imageVideoUrls.like("%." + ext))
			.reduce(BooleanExpression::or)
			.orElse(null);
	}
}
