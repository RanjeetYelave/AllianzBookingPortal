package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.PhaseDTO;
import com.grooming.blog.Entity.Area;
import com.grooming.blog.Entity.Phase;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.AreaRepo;
import com.grooming.blog.Repo.PhaseRepo;
import com.grooming.blog.Services.PhaseService;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public class PhaseServiceImpl implements PhaseService {
	@Autowired
	PhaseRepo phaseRepo;
	@Autowired
	AreaRepo areaRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public PhaseDTO createPhase(PhaseDTO phaseDTO, Integer AreaId) {
		Phase phase = modelMapper.map(phaseDTO, Phase.class);
		Area area = areaRepo.findById(AreaId).orElseThrow(() -> new ResourceNotFoundException("Area", "Id", AreaId));
		phase.setArea(area);
		Phase savedPhase = phaseRepo.save(phase);
		return modelMapper.map(savedPhase, PhaseDTO.class);
	}

	@Override
	public PhaseDTO updatePhase(Integer PhaseId, PhaseDTO phaseDTO) {
		Phase phase = phaseRepo.findById(PhaseId)
				.orElseThrow(() -> new ResourceNotFoundException("Phase", "Id", PhaseId));
		phase.setPhaseName(phaseDTO.getPhaseName());
		Phase savedPhase = phaseRepo.save(phase);
		return modelMapper.map(savedPhase, PhaseDTO.class);
	}

	@Override
	public PhaseDTO getPhasebyId(Integer PhaseId) {
		Phase phase = phaseRepo.findById(PhaseId)
				.orElseThrow(() -> new ResourceNotFoundException("Phase", "Id", PhaseId));
		return modelMapper.map(phase, PhaseDTO.class);
	}

	@Override
	public List<PhaseDTO> getAllPhase() {
		List<Phase> allPhases = phaseRepo.findAll();
		List<PhaseDTO> phaseDtos = allPhases.stream().map(eachphase -> modelMapper.map(eachphase, PhaseDTO.class))
				.collect(Collectors.toList());
		return phaseDtos;
	}

	@Override
	public StandardApiResponseHandler deletePhase(Integer phaseId) {
		Phase phase = phaseRepo.findById(phaseId)
				.orElseThrow(() -> new ResourceNotFoundException("Phase", "Id", phaseId));
		phaseRepo.delete(phase);
		return new StandardApiResponseHandler("phase deleted Successfully", true);
	}

}
