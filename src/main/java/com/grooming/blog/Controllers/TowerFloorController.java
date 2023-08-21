package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.TowerFloorDTO;
import com.grooming.blog.serviceImpl.TowerFloorServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/towerfloor")
public class TowerFloorController {
	@Autowired
	TowerFloorServiceImpl towerFloorServiceImpl;

	@GetMapping("/gettowerfloor")
	ResponseEntity<List<TowerFloorDTO>> getallTowerFloor() {
		List<TowerFloorDTO> allTowerFloor = towerFloorServiceImpl.getAllTowerFloor();
		return new ResponseEntity<List<TowerFloorDTO>>(allTowerFloor, HttpStatus.OK);
	}

	@GetMapping("/gettowerfloorbyId")
	ResponseEntity<TowerFloorDTO> getTowerFloorById(@RequestParam Integer Id) {
		TowerFloorDTO towerFloorbyId = towerFloorServiceImpl.getTowerFloorId(Id);
		return new ResponseEntity<TowerFloorDTO>(towerFloorbyId, HttpStatus.OK);
	}

	@PostMapping("/createtowerfloor")
	ResponseEntity<TowerFloorDTO> createtowerFloor(@Valid @RequestBody TowerFloorDTO towerFloorDTO,
			@RequestParam Integer phaseId) {
		TowerFloorDTO createdTowerFloorDTO = towerFloorServiceImpl.createTowerFloor(towerFloorDTO, phaseId);
		return new ResponseEntity<TowerFloorDTO>(createdTowerFloorDTO, HttpStatus.OK);
	}

	@PutMapping("/updatetowerfloor")
	ResponseEntity<TowerFloorDTO> updateTowerFloor(@Valid @RequestBody TowerFloorDTO towerFloorDTO,
			@RequestParam Integer Id) {
		TowerFloorDTO updatedTowerFloor = towerFloorServiceImpl.updateTowerFloor(Id, towerFloorDTO);
		return new ResponseEntity<TowerFloorDTO>(updatedTowerFloor, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	ResponseEntity<StandardApiResponseHandler> deleteTowerFloor(@RequestParam Integer Id) {
		StandardApiResponseHandler deletedTowerFloor = towerFloorServiceImpl.deleteTowerFloor(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedTowerFloor, HttpStatus.OK);
	}

}
