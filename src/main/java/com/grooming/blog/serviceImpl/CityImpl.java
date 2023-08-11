package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.CityDTO;
import com.grooming.blog.Entity.City;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.AreaRepo;
import com.grooming.blog.Repo.CityRepo;
import com.grooming.blog.Services.CityService;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public class CityImpl implements CityService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CityRepo cityRepo;
	@Autowired
	AreaRepo areaRepo;

	@Override
	public CityDTO createCity(CityDTO cityDTO) {
		City city = modelMapper.map(cityDTO, City.class);
		// city.setCityName(cityDTO.getCityName());
		System.out.println(cityDTO.getCityName());
		City savedCity = cityRepo.save(city);
		return modelMapper.map(savedCity, CityDTO.class);
	}

	@Override
	public CityDTO getCity(Integer cityDId) {
		City foundCity = cityRepo.findById(cityDId)
				.orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityDId));
		CityDTO cityDTO = modelMapper.map(foundCity, CityDTO.class);
		return cityDTO;
	}

	@Override
	public CityDTO updateCity(Integer cityId, CityDTO cityDTO) {
		City foundcity = cityRepo.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
		foundcity.setCityName(cityDTO.getCityName());
		City savedCity = cityRepo.save(foundcity);
		return modelMapper.map(savedCity, CityDTO.class);
	}

	@Override
	public List<CityDTO> getallCities() {
		List<City> allCities = cityRepo.findAll();
		List<CityDTO> AllCitiesDTO = allCities.stream().map(eachcity -> modelMapper.map(eachcity, CityDTO.class))
				.collect(Collectors.toList());
		return AllCitiesDTO;
	}

	@Override
	public StandardApiResponseHandler deleteCity(Integer cityId) {
		City foundCity = cityRepo.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City", "ID", cityId));
		cityRepo.delete(foundCity);

		return new StandardApiResponseHandler("City Deleted with ID " + cityId, true);

	}

}
