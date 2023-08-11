package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.TowerFloorDTO;
import com.grooming.blog.Entity.Phase;
import com.grooming.blog.Entity.TowerFloor;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.PhaseRepo;
import com.grooming.blog.Repo.TowerFloorRepo;
import com.grooming.blog.Services.TowerFloorService;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public class TowerFloorServiceImpl implements TowerFloorService {
	@Autowired
	TowerFloorRepo towerFloorRepo;
	@Autowired
	PhaseRepo phaseRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public TowerFloorDTO createTowerFloor(TowerFloorDTO towerFloorDTO, Integer phaseId) {
		Phase phase = phaseRepo.findById(phaseId)
				.orElseThrow(() -> new ResourceNotFoundException("Phase", "PhaseId", phaseId));
		TowerFloor towerFloor = modelMapper.map(towerFloorDTO, TowerFloor.class);
		towerFloor.setPhase(phase);
		TowerFloor savedTowerFloor = towerFloorRepo.save(towerFloor);
		return modelMapper.map(savedTowerFloor, TowerFloorDTO.class);
	}

	@Override
	public TowerFloorDTO updateTowerFloor(Integer TowerFloorId, TowerFloorDTO towerFloorDTO) {
		TowerFloor towerFloor = towerFloorRepo.findById(TowerFloorId)
				.orElseThrow(() -> new ResourceNotFoundException("TowerFloor", "TowerFloorId", TowerFloorId));

		towerFloor.setTower(towerFloorDTO.getTower());
		towerFloor.setFloor(towerFloorDTO.getFloor());
		TowerFloor savedTowerFloor = towerFloorRepo.save(towerFloor);

		return modelMapper.map(savedTowerFloor, TowerFloorDTO.class);
	}

	@Override
	public TowerFloorDTO getTowerFloorId(Integer TowerFloorId) {
		TowerFloor towerFloor = towerFloorRepo.findById(TowerFloorId)
				.orElseThrow(() -> new ResourceNotFoundException("TowerFloor", "TowerFloorId", TowerFloorId));
		return modelMapper.map(towerFloor, TowerFloorDTO.class);
	}

	@Override
	public List<TowerFloorDTO> getAllTowerFloor() {
		List<TowerFloor> allTowerFloors = towerFloorRepo.findAll();
		List<TowerFloorDTO> allTowerFloorDTOs = allTowerFloors.stream()
				.map(eachTowerFloor -> modelMapper.map(eachTowerFloor, TowerFloorDTO.class))
				.collect(Collectors.toList());
		return allTowerFloorDTOs;
	}

	@Override
	public StandardApiResponseHandler deleteTowerFloor(Integer TowerFloorId) {
		TowerFloor towerFloor = towerFloorRepo.findById(TowerFloorId)
				.orElseThrow(() -> new ResourceNotFoundException("TowerFloor", "TowerFloorId", TowerFloorId));
		towerFloorRepo.delete(towerFloor);
		return new StandardApiResponseHandler("TowerFloor Deleted", true);
	}

}
