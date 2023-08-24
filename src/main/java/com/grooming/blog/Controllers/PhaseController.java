package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.PhaseDTO;
import com.grooming.blog.serviceImpl.PhaseServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RequestMapping("/api/phase")
@RestController
@CrossOrigin(origins = { "http://10.174.0.188:4200", "http://10.174.0.188:62018" })
public class PhaseController {
	@Autowired
	PhaseServiceImpl phaseServiceImpl;

	@GetMapping("/getphasebyId")
	ResponseEntity<PhaseDTO> getPhaseById(@RequestParam Integer Id) {
		PhaseDTO phasebyId = phaseServiceImpl.getPhasebyId(Id);
		return new ResponseEntity<PhaseDTO>(phasebyId, HttpStatus.OK);
	}

	@GetMapping("/getphase")
	ResponseEntity<List<PhaseDTO>> getAllPhases() {
		List<PhaseDTO> allPhase = phaseServiceImpl.getAllPhase();
		return new ResponseEntity<List<PhaseDTO>>(allPhase, HttpStatus.OK);
	}

	@PutMapping("updatephase")
	ResponseEntity<PhaseDTO> updatePhase(@Valid @RequestBody PhaseDTO phaseDTO, @RequestParam Integer Id) {
		PhaseDTO updatePhaseDTO = phaseServiceImpl.updatePhase(Id, phaseDTO);
		return new ResponseEntity<PhaseDTO>(updatePhaseDTO, HttpStatus.OK);
	}

	@PostMapping("/createphase")
	ResponseEntity<PhaseDTO> createPhase(@Valid @RequestBody PhaseDTO phaseDTO, @RequestParam Integer areaId) {
		PhaseDTO createdPhaseDTO = phaseServiceImpl.createPhase(phaseDTO, areaId);
		return new ResponseEntity<PhaseDTO>(createdPhaseDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("delete")
	ResponseEntity<StandardApiResponseHandler> deletePhase(@RequestParam Integer Id) {
		StandardApiResponseHandler deletedPhase = phaseServiceImpl.deletePhase(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedPhase, HttpStatus.OK);
	}
}
