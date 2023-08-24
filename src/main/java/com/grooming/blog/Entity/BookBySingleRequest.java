package com.grooming.blog.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookBySingleRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BookBySingleRequestId;

	private String email;

	private String city;
	private LocalDate Date;
	private String AreaName;
	private String Phase;
	private String Tower;
	private String Floor;
	private String Game;
	private String loginTime;
	private String logoutTime;

}
