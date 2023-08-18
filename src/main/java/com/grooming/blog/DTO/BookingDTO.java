package com.grooming.blog.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

	private int bookingId;
	private TowerFloorDTO towerFloor;
	@NotNull(message = "Please Enter a Date")
	private String date;
	@NotNull(message = "fromTime cannot be Empty")
	private String fromTime;
	@NotNull(message = "toTime cannot be Empty")
	private String toTime;
	@Email(message = "please Enter a valid Email")
	private String EmailId;

	private GameDTO game;
}
