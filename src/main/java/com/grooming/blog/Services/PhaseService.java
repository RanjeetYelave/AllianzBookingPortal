package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.PhaseDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface PhaseService {
	PhaseDTO createPhase(PhaseDTO phaseDTO, Integer AreaId);

	PhaseDTO updatePhase(Integer PhaseId, PhaseDTO phaseDTO);

	PhaseDTO getPhasebyId(Integer PhaseId);

	List<PhaseDTO> getAllPhase();

	StandardApiResponseHandler deletePhase(Integer AreaId);
}
