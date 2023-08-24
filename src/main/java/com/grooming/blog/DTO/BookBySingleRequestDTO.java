package com.grooming.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookBySingleRequestDTO {
	private int BookBySingleRequestId;
	private String email;
	private String city;
	private String currentLocale;
	private String AreaName;
	private String Phase;
	private String Tower;
	private String Floor;
	private String Game;
	private String loginTime;
	private String logoutTime;
}
