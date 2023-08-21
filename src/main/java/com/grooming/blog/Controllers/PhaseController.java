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

import com.grooming.blog.DTO.PhaseDTO;
import com.grooming.blog.serviceImpl.PhaseServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RequestMapping("/api/phase")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhaseController {
	@Autowired
	PhaseServiceImpl phaseServiceImpl;

	@GetMapping("/getphase/{phaseId}")
	ResponseEntity<PhaseDTO> getPhaseById(@PathVariable Integer phaseId) {
		PhaseDTO phasebyId = phaseServiceImpl.getPhasebyId(phaseId);
		return new ResponseEntity<PhaseDTO>(phasebyId, HttpStatus.OK);
	}

	@GetMapping("/getphase")
	ResponseEntity<List<PhaseDTO>> getAllPhases() {
		List<PhaseDTO> allPhase = phaseServiceImpl.getAllPhase();
		return new ResponseEntity<List<PhaseDTO>>(allPhase, HttpStatus.OK);
	}

	@PutMapping("updatephase/{phaseId}")
	ResponseEntity<PhaseDTO> updatePhase(@Valid @RequestBody PhaseDTO phaseDTO, @PathVariable Integer phaseId) {
		PhaseDTO updatePhaseDTO = phaseServiceImpl.updatePhase(phaseId, phaseDTO);
		return new ResponseEntity<PhaseDTO>(updatePhaseDTO, HttpStatus.OK);
	}

	@PostMapping("/createphase/{areaId}")
	ResponseEntity<PhaseDTO> createPhase(@Valid @RequestBody PhaseDTO phaseDTO, @PathVariable Integer areaId) {
		PhaseDTO createdPhaseDTO = phaseServiceImpl.createPhase(phaseDTO, areaId);
		return new ResponseEntity<PhaseDTO>(createdPhaseDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("delete/{phaseId}")
	ResponseEntity<StandardApiResponseHandler> deletePhase(@PathVariable Integer phaseId) {
		StandardApiResponseHandler deletedPhase = phaseServiceImpl.deletePhase(phaseId);
		return new ResponseEntity<StandardApiResponseHandler>(deletedPhase, HttpStatus.OK);
	}
}
