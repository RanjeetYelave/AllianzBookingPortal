package com.grooming.blog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookBySingleRequest {
	@Id
	private int BookBySingleRequestId;
	@Email
	private String email;
	@NotNull(message = "City Name cannot be Empty")
	private String city;
	@NotNull(message = "Date cannot be Empty")
	private String Date;
	@NotNull(message = "Area Name cannot be Empty")
	private String AreaName;
	@NotNull(message = "Phase cannot be Empty")
	private String Phase;
	@NotNull(message = "Tower cannot be Empty")
	private String Tower;
	@NotNull(message = "floor cannot be Empty")
	private int Floor;
	@NotNull(message = "game cannot be Empty")
	private String Game;
	@NotNull(message = "login time cannot be Empty")
	private String loginTime;
	@NotNull(message = "logout time cannot be Empty")
	private String logoutTime;
}
