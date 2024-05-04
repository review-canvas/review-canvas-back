package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
	List<Terms> findAllByMandatory(boolean isMandatory);
}
