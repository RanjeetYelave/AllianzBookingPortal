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

import com.grooming.blog.DTO.AreaDTO;
import com.grooming.blog.serviceImpl.AreaServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/area")
@CrossOrigin(origins = "http://localhost:4200")
public class AreaController {
	@Autowired
	AreaServiceImpl areaServiceImpl;

	@GetMapping("/getarea/{Id}")
	ResponseEntity<AreaDTO> getAreaById(@PathVariable Integer Id) {
		AreaDTO areabyId = areaServiceImpl.getAreabyId(Id);
		return new ResponseEntity<AreaDTO>(areabyId, HttpStatus.OK);

	}

	@GetMapping("/getarea")
	ResponseEntity<List<AreaDTO>> getAllAreas() {
		List<AreaDTO> allArea = areaServiceImpl.getAllArea();
		return new ResponseEntity<List<AreaDTO>>(allArea, HttpStatus.OK);
	}

	@PostMapping("/createarea/{cityId}")
	ResponseEntity<AreaDTO> createArea(@Valid @RequestBody AreaDTO areaDTO, @PathVariable Integer cityId) {
		AreaDTO createArea = areaServiceImpl.createArea(areaDTO, cityId);
		return new ResponseEntity<AreaDTO>(createArea, HttpStatus.CREATED);
	}

	@PutMapping("/updatearea/{Id}")
	ResponseEntity<AreaDTO> updateAreaById(@PathVariable Integer Id, @Valid @RequestBody AreaDTO areaDTO) {
		AreaDTO updateDto = areaServiceImpl.updateArea(Id, areaDTO);
		return new ResponseEntity<AreaDTO>(updateDto, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{areaId}")
	ResponseEntity<StandardApiResponseHandler> deleteArea(@PathVariable Integer areaId) {
		StandardApiResponseHandler deleteArea = areaServiceImpl.deleteArea(areaId);
		return new ResponseEntity<StandardApiResponseHandler>(deleteArea, HttpStatus.GONE);
	}
}
