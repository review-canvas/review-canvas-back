package com.romanticpipe.reviewcanvas.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.romanticpipe.reviewcanvas.domain.QUser;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ReviewType;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewPeriod;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import com.romanticpipe.reviewcanvas.util.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.EnumSet;
import java.util.List;

import static com.romanticpipe.reviewcanvas.domain.QProduct.product;
import static com.romanticpipe.reviewcanvas.domain.QReply.reply;
import static com.romanticpipe.reviewcanvas.domain.QReview.review;
import static com.romanticpipe.reviewcanvas.domain.QUser.user;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

	private static final QUser replyUser = new QUser("replyUser");
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Review> findAllReview(Long productId, Pageable pageable, ReviewFilterForUser filter) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.product.id.eq(productId), getFilterExpression(filter))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.leftJoin(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, replyUser)
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.product.id.eq(productId), getFilterExpression(filter));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<Review> findAllByProductId(Integer shopAdminId, Long productId, Pageable pageable,
										   ReviewPeriod reviewPeriod, EnumSet<ReviewFilterForShopAdmin> reviewFilters,
										   EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.product.shopAdminId.eq(shopAdminId),
				joinProductCondition(productId), getReviewPeriodCondition(reviewPeriod),
				getReviewTypeCondition(reviewFilters), getScoreCondition(score), getReplyExistCondition(replyFilters))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.join(review.product, product)
			.fetchJoin()
			.leftJoin(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, replyUser)
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.product.shopAdminId.eq(shopAdminId),
				joinProductCondition(productId), getReviewPeriodCondition(reviewPeriod),
				getReviewTypeCondition(reviewFilters), getScoreCondition(score), getReplyExistCondition(replyFilters));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<Review> findAllByUserId(Long userId, Pageable pageable, ReviewFilterForUser filter) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.user.id.eq(userId), getFilterExpression(filter))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.leftJoin(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, replyUser)
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.user.id.eq(userId), getFilterExpression(filter));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<Review> findAllByUserIdAndProductId(Long userId, Long productId, Pageable pageable,
													ReviewFilterForUser filter) {
		List<Long> reviewIds = queryFactory.select(review.id)
			.from(review)
			.where(review.user.id.eq(userId).and(review.product.id.eq(productId)), getFilterExpression(filter))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		List<Review> reviewInfoList = queryFactory.selectFrom(review)
			.leftJoin(review.product, product)
			.fetchJoin()
			.leftJoin(review.user, user)
			.fetchJoin()
			.leftJoin(review.replyList, reply)
			.fetchJoin()
			.leftJoin(reply.user, replyUser)
			.fetchJoin()
			.where(review.id.in(reviewIds))
			.orderBy(getReviewOrderSpecifiers(pageable))
			.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(review.count())
			.from(review)
			.where(review.user.id.eq(userId).and(review.product.id.eq(productId)), getFilterExpression(filter));

		return PageableExecutionUtils.getPage(reviewInfoList, pageable, countQuery::fetchOne);
	}

	private OrderSpecifier<?>[] getReviewOrderSpecifiers(Pageable pageable) {
		return QueryDslUtils.getOrderSpecifiers(pageable.getSort(), review).toArray(OrderSpecifier[]::new);
	}

	private BooleanExpression getFilterExpression(ReviewFilterForUser filter) {
		if (filter == ReviewFilterForUser.IMAGE_VIDEO) {
			return review.reviewType.eq(ReviewType.PHOTO).or(review.reviewType.eq(ReviewType.VIDEO));
		} else if (filter == ReviewFilterForUser.GENERAL) {
			return review.reviewType.eq(ReviewType.TEXT);
		}
		return null;
	}

	private BooleanExpression getReviewPeriodCondition(ReviewPeriod reviewPeriod) {
		if (reviewPeriod == null) {
			return null;
		}
		return review.createdAt.between(reviewPeriod.startDate(), reviewPeriod.endDate());
	}

	private BooleanExpression getReviewTypeCondition(EnumSet<ReviewFilterForShopAdmin> filters) {
		if (filters.size() == 1) {
			if (filters.contains(ReviewFilterForShopAdmin.PHOTO)) {
				return review.reviewType.eq(ReviewType.PHOTO);
			} else if (filters.contains(ReviewFilterForShopAdmin.VIDEO)) {
				return review.reviewType.eq(ReviewType.VIDEO);
			} else if (filters.contains(ReviewFilterForShopAdmin.TEXT)) {
				return review.reviewType.eq(ReviewType.TEXT);
			}
		} else if (filters.size() == 2) {
			if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.PHOTO, ReviewFilterForShopAdmin.VIDEO))) {
				return review.reviewType.in(ReviewType.PHOTO, ReviewType.VIDEO);
			} else if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.PHOTO, ReviewFilterForShopAdmin.TEXT))) {
				return review.reviewType.in(ReviewType.PHOTO, ReviewType.TEXT);
			} else if (filters.containsAll(EnumSet.of(ReviewFilterForShopAdmin.VIDEO, ReviewFilterForShopAdmin.TEXT))) {
				return review.reviewType.in(ReviewType.VIDEO, ReviewType.TEXT);
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

	private BooleanExpression joinProductCondition(Long productId) {
		if (productId == null || productId <= 0) {
			return null;
		}
		return review.product.id.eq(productId);
	}
}
