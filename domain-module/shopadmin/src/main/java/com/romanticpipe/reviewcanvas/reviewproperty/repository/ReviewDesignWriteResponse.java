package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignWrite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewDesignWriteResponse extends JpaRepository<ReviewDesignWrite, Integer> {
	Optional<ReviewDesignWrite> findByShopAdminId(Integer shopAdminId);
}
