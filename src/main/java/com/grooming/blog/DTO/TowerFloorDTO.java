package com.grooming.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TowerFloorDTO {
	private int Id;
	private String Tower;
	private String Floor;
	private PhaseDTO phase;
}
