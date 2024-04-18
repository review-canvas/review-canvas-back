package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NotBlank
	private String memberId;
	@NotBlank
	private String name;
	@NotBlank
	private String nickName;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Gender gender;
	@NotBlank
	private String nationality;
	@NotNull
	private LocalDate birth;

	@Builder
	public User(String memberId, String name, String nickName, Gender gender, String nationality, LocalDate birth) {
		this.memberId = memberId;
		this.name = name;
		this.nickName = nickName;
		this.gender = gender;
		this.nationality = nationality;
		this.birth = birth;
	}
}
