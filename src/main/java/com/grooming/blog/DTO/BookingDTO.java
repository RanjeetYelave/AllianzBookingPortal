package com.grooming.blog.DTO;

import java.time.LocalTime;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

	private int bookingId;

	private TowerFloorDTO towerFloor;
	private Date date;
	private LocalTime fromTime;
	private LocalTime toTime;
	private String EmailId;
}
