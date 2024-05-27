package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

	@Transient
	private final UserEncryptor userEncryptor = new UserEncryptor();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long id;
	private String memberId;
	private String mallId;
	private String name;
	private String nickName;
	private String gender;
	private String nationality;
	private String birth;

	@Builder
	public User(String memberId, String mallId, String name, String nickName, Gender gender, String nationality,
				LocalDate birth) {
		this.memberId = memberId;
		this.mallId = mallId;
		this.name = userEncryptor.encrypt(name);
		this.nickName = userEncryptor.encrypt(nickName);
		this.gender = userEncryptor.encrypt(gender.name());
		this.nationality = userEncryptor.encrypt(nationality);
		this.birth = userEncryptor.encrypt(birth.toString());
	}

	public String getName() {
		return userEncryptor.decrypt(name);
	}

	public String getNickName() {
		return userEncryptor.decrypt(nickName);
	}

	public Gender getGender() {
		return Gender.valueOf(userEncryptor.decrypt(gender));
	}

	public String getNationality() {
		return userEncryptor.decrypt(nationality);
	}

	public LocalDate getBirth() {
		return LocalDate.parse(userEncryptor.decrypt(birth));
	}
}
