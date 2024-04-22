package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase;

import com.romanticpipe.reviewcanvas.TestReviewDesignFactory;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.service.AdminAuthCreater;
import com.romanticpipe.reviewcanvas.service.MyReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ReviewDesignReader;
import com.romanticpipe.reviewcanvas.service.ReviewDesignValidator;
import com.romanticpipe.reviewcanvas.service.ReviewVisibilityReader;
import com.romanticpipe.reviewcanvas.service.ShopAdminCreator;
import com.romanticpipe.reviewcanvas.service.ShopAdminValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShopAdminUseCaseImplTest {

	@Mock
	PasswordEncoder passwordEncoder;
	@Mock
	AdminAuthCreater adminAuthCreater;
	@Mock
	ShopAdminCreator shopAdminCreator;
	@Mock
	ShopAdminValidator shopAdminValidator;
	@Mock
	ReviewVisibilityReader reviewVisibilityReader;
	@Mock
	ReviewDesignReader reviewDesignReader;
	@Mock
	ReviewDesignValidator reviewDesignValidator;
	@Mock
	MyReviewDesignValidator myReviewDesignValidator;
	@InjectMocks
	ShopAdminUseCaseImpl shopAdminUseCase;

	@Nested
	@DisplayName("getReviewsByProductId 메소드는")
	class UpdateReviewDesignTest {
		@Test
		@DisplayName("리뷰 디자인 아이디로 조회한 리뷰 디자인을 dto에 맞게 수정한다.")
		void it_changes_review_design_by_review_design_id() {
			//given
			var shopAdminId = 2;
			var reviewDesignId = 1;
			var updateReviewDesignRequest = new UpdateReviewDesignRequest(
				ReviewDesignPosition.REVIEW_LIST, "test", "BOARD", "0px", "0px", "#000000", 0, "#ffffff", 0, 0,
				"NUMBER", 0, "left", "#000000", "STAR", 1, "urlurl"
			);
			var reviewDesign = TestReviewDesignFactory.createReviewDesign(1, "test", "BOARD", "0px", "0px", "#000000",
				0, "#ffffff", 0, 0,
				"NUMBER", 0, "left", "#000000", "STAR", 1, "url"
			);
			given(reviewDesignValidator.validById(eq(reviewDesignId))).willReturn(reviewDesign);

			//when
			shopAdminUseCase.updateReviewDesign(shopAdminId, reviewDesignId, updateReviewDesignRequest);

			//then
			assertThat(reviewDesign.getReviewDesignUrl()).isEqualTo(updateReviewDesignRequest.reviewDesignUrl());
		}

	}

}
