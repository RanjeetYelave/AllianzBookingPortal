package com.grooming.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhaseDTO {
	private int phaseId;
	private String phaseName;
	private AreaDTO area;
}
