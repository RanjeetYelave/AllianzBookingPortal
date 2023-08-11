package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.AreaDTO;
import com.grooming.blog.Entity.Area;
import com.grooming.blog.Entity.City;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.AreaRepo;
import com.grooming.blog.Repo.CityRepo;
import com.grooming.blog.Services.AreaService;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	AreaRepo areaRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CityRepo cityRepo;

	@Override
	public AreaDTO createArea(AreaDTO areaDTO, Integer cityId) {
		Area area = modelMapper.map(areaDTO, Area.class);
		City city = cityRepo.findById(cityId).orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
		area.setCity(city);
		Area savedArea = areaRepo.save(area);
		return modelMapper.map(savedArea, AreaDTO.class);
	}

	@Override
	public AreaDTO updateArea(int AreaId, AreaDTO areaDTO) {
		Area newArea = modelMapper.map(areaDTO, Area.class);
		Area existingArea = areaRepo.findById(AreaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area", "AreaId", AreaId));

		existingArea.setAreaName(newArea.getAreaName());
		Area savedArea = areaRepo.save(existingArea);
		return modelMapper.map(savedArea, AreaDTO.class);
	}

	@Override
	public AreaDTO getAreabyId(int AreaId) {
		Area foundArea = areaRepo.findById(AreaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area", "AreaId", AreaId));
		AreaDTO areaDTO = modelMapper.map(foundArea, AreaDTO.class);
		return areaDTO;
	}

	@Override
	public List<AreaDTO> getAllArea() {
		List<Area> AllAreas = areaRepo.findAll();
		List<AreaDTO> AllAreaDTOs = AllAreas.stream().map(eachArea -> modelMapper.map(eachArea, AreaDTO.class))
				.collect(Collectors.toList());
		return AllAreaDTOs;
	}

	@Override
	public StandardApiResponseHandler deleteArea(int AreaId) {
		Area foundArea = areaRepo.findById(AreaId)
				.orElseThrow(() -> new ResourceNotFoundException("Area", "AreaID", AreaId));

		areaRepo.delete(foundArea);
		return new StandardApiResponseHandler("Area Deleted Successfully!", true);
	}

	@Override
	public List<AreaDTO> getAllAreasByCity(Integer CityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaDTO> searchByKeyword(String Keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
