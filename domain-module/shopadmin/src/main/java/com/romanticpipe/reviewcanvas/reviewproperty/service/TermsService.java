package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.TermsErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {
	private final TermsRepository termsRepository;

	public void validateMandatoryTerms(List<Integer> consentedTermsIds) {
		boolean allMandatoryTermsSelected = termsRepository.findAllByMandatory(true).stream()
			.allMatch(mandatoryTerms -> consentedTermsIds.contains(mandatoryTerms.getId()));

		if (!allMandatoryTermsSelected) {
			throw new BusinessException(TermsErrorCode.NOT_CONSENT_MANDATORY_TERMS);
		}
	}

	public void validateAllById(List<Integer> termsIds) {
		List<Integer> storedTermsIds = termsRepository.findAll().stream().map(Terms::getId).toList();
		boolean allTermsIdMatched = new HashSet<>(storedTermsIds).containsAll(termsIds);

		if (!allTermsIdMatched) {
			throw new BusinessException(TermsErrorCode.TERMS_ERROR_CODE);
		}
	}
}
