package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.TowerFloorDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface TowerFloorService {
	TowerFloorDTO createTowerFloor(TowerFloorDTO towerFloorDTO, Integer phaseId);

	TowerFloorDTO updateTowerFloor(Integer TowerFloorId, TowerFloorDTO towerFloorDTO);

	TowerFloorDTO getTowerFloorId(Integer TowerFloorId);

	List<TowerFloorDTO> getAllTowerFloor();

	StandardApiResponseHandler deleteTowerFloor(Integer TowerFloorId);
}
