package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.TowerFloorDTO;
import com.grooming.blog.serviceImpl.TowerFloorServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/towerfloor")
@CrossOrigin(origins = "http://localhost:4200")
public class TowerFloorController {
	@Autowired
	TowerFloorServiceImpl towerFloorServiceImpl;

	@GetMapping("/gettowerfloor")
	ResponseEntity<List<TowerFloorDTO>> getallTowerFloor() {
		List<TowerFloorDTO> allTowerFloor = towerFloorServiceImpl.getAllTowerFloor();
		return new ResponseEntity<List<TowerFloorDTO>>(allTowerFloor, HttpStatus.OK);
	}

	@GetMapping("/gettowerfloor/{Id}")
	ResponseEntity<TowerFloorDTO> getTowerFloorById(@PathVariable Integer Id) {
		TowerFloorDTO towerFloorbyId = towerFloorServiceImpl.getTowerFloorId(Id);
		return new ResponseEntity<TowerFloorDTO>(towerFloorbyId, HttpStatus.OK);
	}

	@PostMapping("/createtowerfloor/{phaseId}")
	ResponseEntity<TowerFloorDTO> createtowerFloor(@Valid @RequestBody TowerFloorDTO towerFloorDTO,
			@PathVariable Integer phaseId) {
		TowerFloorDTO createdTowerFloorDTO = towerFloorServiceImpl.createTowerFloor(towerFloorDTO, phaseId);
		return new ResponseEntity<TowerFloorDTO>(createdTowerFloorDTO, HttpStatus.OK);
	}

	@PutMapping("/updatetowerfloor/{Id}")
	ResponseEntity<TowerFloorDTO> updateTowerFloor(@Valid @RequestBody TowerFloorDTO towerFloorDTO,
			@PathVariable Integer Id) {
		TowerFloorDTO updatedTowerFloor = towerFloorServiceImpl.updateTowerFloor(Id, towerFloorDTO);
		return new ResponseEntity<TowerFloorDTO>(updatedTowerFloor, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{Id}")
	ResponseEntity<StandardApiResponseHandler> deleteTowerFloor(@PathVariable Integer Id) {
		StandardApiResponseHandler deletedTowerFloor = towerFloorServiceImpl.deleteTowerFloor(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedTowerFloor, HttpStatus.OK);
	}

}
