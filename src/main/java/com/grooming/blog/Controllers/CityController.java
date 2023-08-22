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

import com.grooming.blog.DTO.CityDTO;
import com.grooming.blog.Services.CityService;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/city")
@CrossOrigin(origins = "http://10.174.0.188:4200")
public class CityController {
	@Autowired
	CityService cityImpl;

	@PostMapping("/createcity")
	ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) {
		CityDTO createdCity = cityImpl.createCity(cityDTO);
		return new ResponseEntity<CityDTO>(createdCity, HttpStatus.CREATED);
	}

	@GetMapping("/getcitybyId")
	ResponseEntity<CityDTO> getCity(@RequestParam Integer Id) {
		CityDTO cityDTO = cityImpl.getCity(Id);
		return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
	}

	@GetMapping("/getcity")
	ResponseEntity<List<CityDTO>> getAllCities() {
		List<CityDTO> allcities = cityImpl.getallCities();
		return new ResponseEntity<List<CityDTO>>(allcities, HttpStatus.OK);
	}

	@PutMapping("/updatecity")
	ResponseEntity<CityDTO> updateCity(@RequestParam Integer Id, @Valid @RequestBody CityDTO cityDto) {
		CityDTO updatedCity = cityImpl.updateCity(Id, cityDto);
		return new ResponseEntity<CityDTO>(updatedCity, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	ResponseEntity<StandardApiResponseHandler> deleteCity(@RequestParam Integer Id) {
		StandardApiResponseHandler response = cityImpl.deleteCity(Id);
		return new ResponseEntity<StandardApiResponseHandler>(response, HttpStatus.GONE);
	}

}
