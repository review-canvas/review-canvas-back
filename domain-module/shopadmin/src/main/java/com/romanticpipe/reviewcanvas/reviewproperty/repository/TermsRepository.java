package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
	public Optional<Terms> findByTag(String tag);
}
