package com.grooming.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaDTO {
	private int Id;
	private String areaName;
	private CityDTO city;

}
