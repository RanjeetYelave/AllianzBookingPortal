package com.grooming.blog.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaDTO {
	private int Id;
	@NotNull
	private String areaName;

	private CityDTO city;

}
