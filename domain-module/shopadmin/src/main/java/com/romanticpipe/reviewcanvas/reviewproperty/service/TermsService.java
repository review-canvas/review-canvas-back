package com.romanticpipe.reviewcanvas.reviewproperty.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.TermsErrorCode;
import com.romanticpipe.reviewcanvas.reviewproperty.exception.TermsNotFoundException;
import com.romanticpipe.reviewcanvas.reviewproperty.repository.TermsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TermsService {
	private final TermsRepository termsRepository;

	public Terms validateByTag(String tag) {
		return termsRepository.findByTag(tag).orElseThrow(TermsNotFoundException::new);
	}

	public void validateMandatoryTerms(List<String> termsTags) {
		List<Terms> mandatoryTermsList = termsRepository.findAllByMandatory(true);
		for (Terms terms : mandatoryTermsList) {
			if (!termsTags.contains(terms.getTag()))
				throw new BusinessException(TermsErrorCode.NOT_CONSENT_MANDATORY_TERMS);
		}
	}
}
