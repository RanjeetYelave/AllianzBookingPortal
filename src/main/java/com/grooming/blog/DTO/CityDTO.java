package com.grooming.blog.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityDTO {
	private int id;
	@NotNull(message = "Should not be Empty")
	@Size(min = 2, message = "Enter Valid City Name")
	private String CityName;

}
