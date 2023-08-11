package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.AreaDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface AreaService {

	AreaDTO createArea(AreaDTO areaDTO, Integer cityId);

	AreaDTO updateArea(int AreaId, AreaDTO areaDTO);

	AreaDTO getAreabyId(int AreaId);

	List<AreaDTO> getAllArea();

	StandardApiResponseHandler deleteArea(int AreaId);

	List<AreaDTO> getAllAreasByCity(Integer CityId);

	List<AreaDTO> searchByKeyword(String Keyword);
}
