package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.romanticpipe.reviewcanvas.TestProductFactory;
import com.romanticpipe.reviewcanvas.TestReviewColumnFactory;
import com.romanticpipe.reviewcanvas.TestReviewFactory;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.ReviewColumnRepository;

@ExtendWith(MockitoExtension.class)
class ReviewColumnUseCaseImplTest {

	@Mock
	ReviewColumnRepository reviewColumnRepository;
	@InjectMocks
	ReviewColumnUseCaseImpl reviewColumnUseCase;

	@Nested
	@DisplayName("getReviewColumn 메소드는")
	class getReviewColumn {

		@Test
		@DisplayName("shop admin id로 review-column 컬럼을 조회하여 반환한다.")
		void it_returns_review_column_by_shop_admin_id() {
			// given
			var shopAdminId = 1;
			// var reviewColumn = TestReviewColumnFactory.createReviewColumn();
			
			// when
			var result = reviewColumnUseCase.getColumnByShopAdminId(shopAdminId);

			// then
			// Assertions.assertThat(result).isEqualTo(ReviewColumn.map(GetReviewResponse::from));
		}
	}

}
