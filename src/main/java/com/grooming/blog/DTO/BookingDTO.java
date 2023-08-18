package com.grooming.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

	private int bookingId;
	private TowerFloorDTO towerFloor;
	private String date;
	private String fromTime;
	private String toTime;
	private String EmailId;

	private GameDTO game;
}
