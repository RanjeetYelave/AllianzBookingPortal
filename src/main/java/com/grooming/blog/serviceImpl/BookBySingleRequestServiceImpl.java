package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.AreaDTO;
import com.grooming.blog.DTO.BookBySingleRequestDTO;
import com.grooming.blog.DTO.CityDTO;
import com.grooming.blog.DTO.GameDTO;
import com.grooming.blog.DTO.PhaseDTO;
import com.grooming.blog.DTO.TowerFloorDTO;
import com.grooming.blog.Entity.Area;
import com.grooming.blog.Entity.BookBySingleRequest;
import com.grooming.blog.Entity.City;
import com.grooming.blog.Entity.Game;
import com.grooming.blog.Entity.Phase;
import com.grooming.blog.Entity.TowerFloor;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.AreaRepo;
import com.grooming.blog.Repo.BookBySingleRequestRepo;
import com.grooming.blog.Repo.CityRepo;
import com.grooming.blog.Repo.GameRepo;
import com.grooming.blog.Repo.PhaseRepo;
import com.grooming.blog.Repo.TowerFloorRepo;
import com.grooming.blog.Services.AreaService;
import com.grooming.blog.Services.BookBySingleRequestService;
import com.grooming.blog.Services.CityService;
import com.grooming.blog.Services.GameService;
import com.grooming.blog.Services.PhaseService;
import com.grooming.blog.Services.TowerFloorService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class BookBySingleRequestServiceImpl implements BookBySingleRequestService {
	@Autowired
	BookBySingleRequestRepo bookBySingleRequestRepo;
	@Autowired
	CityRepo cityRepo;
	@Autowired
	AreaRepo areaRepo;
	@Autowired
	PhaseRepo phaseRepo;
	@Autowired
	TowerFloorRepo towerFloorRepo;
	@Autowired
	GameRepo gameRepo;
	@Autowired
	CityService cityService;
	@Autowired
	AreaService areaService;
	@Autowired
	PhaseService phaseService;
	@Autowired
	TowerFloorService towerFloorService;
	@Autowired
	GameService gameService;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public BookBySingleRequestDTO createBooking(BookBySingleRequestDTO bookBySingleRequestDTO) {
		// city
		String city = bookBySingleRequestDTO.getCity();
		CityDTO cityDTO = new CityDTO();
		cityDTO.setCityName(city);
		CityDTO createdCityDTO = cityService.createCity(cityDTO);
		City createdCity = modelMapper.map(createdCityDTO, City.class);
		cityRepo.save(createdCity);

		// area
		String areaName = bookBySingleRequestDTO.getAreaName();
		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setAreaName(areaName);
		AreaDTO createdAreaDTO = areaService.createArea(areaDTO, createdCity.getId());
		Area createdArea = modelMapper.map(createdAreaDTO, Area.class);
		areaRepo.save(createdArea);

		// phase
		String phase = bookBySingleRequestDTO.getPhase();
		PhaseDTO phaseDTO = new PhaseDTO();
		phaseDTO.setPhaseName(phase);
		PhaseDTO createdPhaseDTO = phaseService.createPhase(phaseDTO, createdArea.getId());
		Phase createdPhase = modelMapper.map(createdPhaseDTO, Phase.class);
		phaseRepo.save(createdPhase);

		// towerfloor
		String tower = bookBySingleRequestDTO.getTower();
		int floor = bookBySingleRequestDTO.getFloor();
		TowerFloorDTO towerFloorDTO = new TowerFloorDTO();
		towerFloorDTO.setTower(tower);
		towerFloorDTO.setFloor(floor);
		TowerFloorDTO createTowerFloorDTO = towerFloorService.createTowerFloor(towerFloorDTO,
				createdPhase.getPhaseId());
		TowerFloor createdTowerFloor = modelMapper.map(createTowerFloorDTO, TowerFloor.class);
		towerFloorRepo.save(createdTowerFloor);

		// game
		String game = bookBySingleRequestDTO.getGame();
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameName(game);
		GameDTO createdGameDto = gameService.createGame(gameDTO);
		Game createdGame = modelMapper.map(createdGameDto, Game.class);
		gameRepo.save(createdGame);

		// bookBySingleRequestTableMapping
		BookBySingleRequest createdbooking = modelMapper.map(bookBySingleRequestDTO, BookBySingleRequest.class);
		BookBySingleRequest savedBooking = bookBySingleRequestRepo.save(createdbooking);

		return modelMapper.map(savedBooking, BookBySingleRequestDTO.class);
	}

	@Override
	public BookBySingleRequestDTO getBookingById(Integer Id) {
		BookBySingleRequest bookingById = bookBySingleRequestRepo.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingID", Id));
		BookBySingleRequestDTO bookBySingleRequestDTO = modelMapper.map(bookingById, BookBySingleRequestDTO.class);
		return bookBySingleRequestDTO;
	}

	@Override
	public List<BookBySingleRequestDTO> getAllBookings() {
		List<BookBySingleRequest> findAll = bookBySingleRequestRepo.findAll();
		List<BookBySingleRequestDTO> collect = findAll.stream()
				.map(eachBooking -> modelMapper.map(eachBooking, BookBySingleRequestDTO.class))
				.collect(Collectors.toList());
		return collect;
	}

}
