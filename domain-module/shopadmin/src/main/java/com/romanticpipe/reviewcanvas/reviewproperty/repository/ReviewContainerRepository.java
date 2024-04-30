package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;

@Repository
public interface ReviewContainerRepository extends JpaRepository<ReviewContainer, Integer> {
	Optional<ReviewContainer> findByShopAdminId(Integer shopAdminId);
}
