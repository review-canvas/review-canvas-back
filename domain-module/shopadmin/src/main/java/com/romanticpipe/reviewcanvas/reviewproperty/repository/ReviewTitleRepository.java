package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;

public interface ReviewTitleRepository extends JpaRepository<ReviewTitle, Integer> {
}
