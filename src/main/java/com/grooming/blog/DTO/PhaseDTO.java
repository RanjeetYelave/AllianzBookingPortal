package com.grooming.blog.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhaseDTO {
	private int phaseId;
	@NotNull(message = "Phase Name cannot be Empty")
	private String phaseName;
	private AreaDTO area;
}
