package com.romanticpipe.reviewcanvas.reviewproperty.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Terms;
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
}
