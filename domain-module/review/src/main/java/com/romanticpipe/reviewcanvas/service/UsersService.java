package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;

	public Optional<User> findUser(String memberId, String mallId) {
		return usersRepository.findByMemberIdAndMallId(memberId, mallId);
	}
}
