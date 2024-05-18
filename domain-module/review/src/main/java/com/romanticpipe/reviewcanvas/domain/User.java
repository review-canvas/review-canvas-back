package com.romanticpipe.reviewcanvas.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long id;

	private String memberId;
	private String mallId;
	private String name;
	private String nickName;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Gender gender;
	private String nationality;
	private LocalDate birth;

	@Builder
	public User(String memberId, String mallId, String name, String nickName, Gender gender, String nationality,
		LocalDate birth) {
		this.memberId = memberId;
		this.mallId = mallId;
		this.name = name;
		this.nickName = nickName;
		this.gender = gender;
		this.nationality = nationality;
		this.birth = birth;
	}
}
