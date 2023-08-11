package com.grooming.blog.DTO;

import java.time.LocalTime;
import java.util.Date;

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
	private Date date;
	@NotNull(message = "fromTime cannot be Empty")
	private LocalTime fromTime;
	@NotNull(message = "toTime cannot be Empty")
	private LocalTime toTime;
	@Email(message = "please Enter a valid Email")
	private String EmailId;

	private GameDTO game;
}
