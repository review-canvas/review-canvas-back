package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
	Optional<Terms> findByTag(String tag);

	List<Terms> findAllByMandatory(boolean isMandatory);
}
