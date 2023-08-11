package com.grooming.blog.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.CityDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public interface CityService {
	CityDTO createCity(CityDTO CityDTO);

	CityDTO getCity(Integer cityId);

	CityDTO updateCity(Integer cityId, CityDTO cityDTO);

	List<CityDTO> getallCities();

	StandardApiResponseHandler deleteCity(Integer cityId);
}
