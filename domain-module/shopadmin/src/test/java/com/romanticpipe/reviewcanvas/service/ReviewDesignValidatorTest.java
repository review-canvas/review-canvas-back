package com.romanticpipe.reviewcanvas.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.romanticpipe.reviewcanvas.TestReviewDesignFactory;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;

@ExtendWith(MockitoExtension.class)
class ReviewDesignValidatorTest {

	@Mock
	ReviewDesignRepository reviewDesignRepository;
	@InjectMocks
	ReviewDesignValidator reviewDesignValidator;

	@Nested
	@DisplayName("validById 메소드는")
	class ValidByIdTest {
		@Test
		@DisplayName("리뷰 디자인 아이디를 통해 ReviewDesign 엔티티를 반환한다.")
		void it_returns_review_design_by_review_design_id() {
			//given
			Long reviewDesignId = 1L;
			var reviewDesign = TestReviewDesignFactory.createReviewDesign(1L, "test", "BOARD", "0px", "0px", "#000000",
				0, "#ffffff", 0, 0,
				"NUMBER", 0, "left", "#000000", "STAR", 1, "url"
			);
			given(reviewDesignRepository.findById(eq(reviewDesignId))).willReturn(Optional.of(reviewDesign));
			//when
			var result = reviewDesignValidator.validById(reviewDesignId);
			//then
			assertThat(result).isEqualTo(reviewDesign);
		}
	}
}
