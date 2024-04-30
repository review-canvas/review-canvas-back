package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

public interface ReviewColumnRepository extends JpaRepository<ReviewColumn, Integer> {
}
