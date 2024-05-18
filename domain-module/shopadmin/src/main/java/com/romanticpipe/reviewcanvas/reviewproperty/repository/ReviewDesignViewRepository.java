package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewDesignViewRepository extends JpaRepository<ReviewDesignView, Integer> {
	Optional<ReviewDesignView> findByShopAdminId(Integer shopAdminId);
}
