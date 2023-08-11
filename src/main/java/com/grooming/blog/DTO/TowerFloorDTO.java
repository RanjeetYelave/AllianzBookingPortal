package com.grooming.blog.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TowerFloorDTO {
	private int Id;
	@NotNull(message = "Please enter tower No")
	private int Tower;
	@NotNull(message = "Please enter floor")
	private String Floor;
	private PhaseDTO phase;
}
