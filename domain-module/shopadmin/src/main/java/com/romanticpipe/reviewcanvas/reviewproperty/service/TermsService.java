package com.romanticpipe.reviewcanvas.reviewproperty.service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.TermsErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
