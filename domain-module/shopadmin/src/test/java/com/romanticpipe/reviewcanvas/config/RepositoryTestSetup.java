package com.romanticpipe.reviewcanvas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.TestReviewDesignFactory;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;

@DataJpaTest
@Transactional
public abstract class RepositoryTestSetup {

	@Autowired
	protected ReviewDesignRepository reviewDesignRepository;

	protected ReviewDesign createReviewDesignEntity() {
		ReviewDesign reviewDesign = TestReviewDesignFactory.createReviewDesign("test", "BOARD", "0px", "0px", "#000000",
			0, "#ffffff", 0, 0,
			"NUMBER", 0, "left", "#000000", "STAR", 1, "url"
		);
		return reviewDesignRepository.save(reviewDesign);
	}
}
