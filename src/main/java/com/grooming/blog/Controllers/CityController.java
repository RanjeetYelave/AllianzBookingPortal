package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.CityDTO;
import com.grooming.blog.Services.CityService;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/city")
public class CityController {
	@Autowired
	CityService cityImpl;

	@PostMapping("/createcity")
	ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) {
		CityDTO createdCity = cityImpl.createCity(cityDTO);
		return new ResponseEntity<CityDTO>(createdCity, HttpStatus.CREATED);
	}

	@GetMapping("/getcity/{cityId}")
	ResponseEntity<CityDTO> getCity(@PathVariable Integer cityId) {
		CityDTO cityDTO = cityImpl.getCity(cityId);
		return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
	}

	@GetMapping("/getcity")
	ResponseEntity<List<CityDTO>> getAllCities() {
		List<CityDTO> allcities = cityImpl.getallCities();
		return new ResponseEntity<List<CityDTO>>(allcities, HttpStatus.OK);
	}

	@PutMapping("/updatecity/{cityId}")
	ResponseEntity<CityDTO> updateCity(@PathVariable Integer cityId, @Valid @RequestBody CityDTO cityDto) {
		CityDTO updatedCity = cityImpl.updateCity(cityId, cityDto);
		return new ResponseEntity<CityDTO>(updatedCity, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{cityId}")
	ResponseEntity<StandardApiResponseHandler> deleteCity(@PathVariable Integer cityId) {
		StandardApiResponseHandler response = cityImpl.deleteCity(cityId);
		return new ResponseEntity<StandardApiResponseHandler>(response, HttpStatus.GONE);
	}

}
