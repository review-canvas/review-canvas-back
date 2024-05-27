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
import org.springframework.util.StringUtils;

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
		this.nickName = StringUtils.hasText(nickName) ? userEncryptor.encrypt(nickName) : null;
		this.gender = gender != null ? userEncryptor.encrypt(gender.name()) : null;
		this.nationality = StringUtils.hasText(nationality) ? userEncryptor.encrypt(nationality) : null;
		this.birth = birth != null ? userEncryptor.encrypt(birth.toString()) : null;
	}

	public String getName() {
		return userEncryptor.decrypt(name);
	}

	public String getNickName() {
		return StringUtils.hasText(nickName) ? userEncryptor.decrypt(nickName) : null;
	}

	public Gender getGender() {
		return gender != null ? Gender.valueOf(userEncryptor.decrypt(gender)) : null;
	}

	public String getNationality() {
		return StringUtils.hasText(nationality) ? userEncryptor.decrypt(nationality) : null;
	}

	public LocalDate getBirth() {
		return birth != null ? LocalDate.parse(userEncryptor.decrypt(birth)) : null;
	}

	public void update(String name, String nickName, String gender, String nationality, LocalDate birthday) {
		this.name = userEncryptor.encrypt(name);
		this.nickName = StringUtils.hasText(nickName) ? userEncryptor.encrypt(nickName) : null;
		this.gender = gender != null ? userEncryptor.encrypt(gender) : null;
		this.nationality = StringUtils.hasText(nationality) ? userEncryptor.encrypt(nationality) : null;
		this.birth = StringUtils.hasText(birth) ? userEncryptor.encrypt(birthday.toString()) : null;
	}
}
