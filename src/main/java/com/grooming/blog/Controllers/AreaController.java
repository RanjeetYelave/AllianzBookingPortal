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

import com.grooming.blog.DTO.AreaDTO;
import com.grooming.blog.serviceImpl.AreaServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/area")
@CrossOrigin(origins = "http://10.174.0.188:4200")
public class AreaController {
	@Autowired
	AreaServiceImpl areaServiceImpl;

	@GetMapping("/getareabyId")
	ResponseEntity<AreaDTO> getAreaById(@RequestParam Integer Id) {
		AreaDTO areabyId = areaServiceImpl.getAreabyId(Id);
		return new ResponseEntity<AreaDTO>(areabyId, HttpStatus.OK);

	}

	@GetMapping("/getarea")
	ResponseEntity<List<AreaDTO>> getAllAreas() {
		List<AreaDTO> allArea = areaServiceImpl.getAllArea();
		return new ResponseEntity<List<AreaDTO>>(allArea, HttpStatus.OK);
	}

	@PostMapping("/createarea")
	ResponseEntity<AreaDTO> createArea(@Valid @RequestBody AreaDTO areaDTO, @RequestParam Integer cityId) {
		AreaDTO createArea = areaServiceImpl.createArea(areaDTO, cityId);
		return new ResponseEntity<AreaDTO>(createArea, HttpStatus.CREATED);
	}

	@PutMapping("/updatearea")
	ResponseEntity<AreaDTO> updateAreaById(@RequestParam Integer Id, @Valid @RequestBody AreaDTO areaDTO) {
		AreaDTO updateDto = areaServiceImpl.updateArea(Id, areaDTO);
		return new ResponseEntity<AreaDTO>(updateDto, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	ResponseEntity<StandardApiResponseHandler> deleteArea(@RequestParam Integer Id) {
		StandardApiResponseHandler deleteArea = areaServiceImpl.deleteArea(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deleteArea, HttpStatus.GONE);
	}
}
