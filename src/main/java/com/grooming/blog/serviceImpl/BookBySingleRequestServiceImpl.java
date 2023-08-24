package com.grooming.blog.serviceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
import com.grooming.blog.utils.StandardApiResponseHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
//dateconversion

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(bookBySingleRequestDTO.getCurrentLocale(), inputFormatter);

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
		String floor = bookBySingleRequestDTO.getFloor();
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
		/*
		 * BookBySingleRequest createdbooking = modelMapper.map(bookBySingleRequestDTO,
		 * BookBySingleRequest.class); BookBySingleRequest savedBooking =
		 * bookBySingleRequestRepo.save(createdbooking);
		 */

		BookBySingleRequest bookBySingleRequest = new BookBySingleRequest();
		bookBySingleRequest.setAreaName(areaName);
		bookBySingleRequest.setCity(city);
		bookBySingleRequest.setEmail(bookBySingleRequestDTO.getEmail());
		bookBySingleRequest.setFloor(floor);
		bookBySingleRequest.setTower(tower);
		bookBySingleRequest.setDate(date);
		bookBySingleRequest.setLoginTime(bookBySingleRequestDTO.getLoginTime());
		bookBySingleRequest.setLogoutTime(bookBySingleRequestDTO.getLogoutTime());
		bookBySingleRequest.setGame(game);
		bookBySingleRequest.setPhase(phase);
		BookBySingleRequest savedBooking = bookBySingleRequestRepo.save(bookBySingleRequest);

		// email
		sendEmail(bookBySingleRequestDTO);
		return modelMapper.map(savedBooking, BookBySingleRequestDTO.class);
	}

	private void sendEmail(BookBySingleRequestDTO bookBySingleRequestDTO) {
		OkHttpClient client = new OkHttpClient().newBuilder()

				.build();

		MediaType mediaType = MediaType.parse("application/json");

		String message = " <html> <style> table, th, td {   border:1px solid black; } </style> <body>  <h2>Your booking is confirmed as per below details :</h2>  <table style=\\\"width:100%\\\">   <tr>     <th>Game Name</th>     <th>From</th>     <th>To</th>   </tr>   <tr>     <td>"
				+ bookBySingleRequestDTO.getGame() + "</td>     <td>" + bookBySingleRequestDTO.getLoginTime()
				+ "</td>     <td>" + bookBySingleRequestDTO.getLogoutTime()
				+ "</td>   </tr> </table>  <p>In case of any queries, please drop a mail to below mailbox : A-AIN-APP1-RC03-ITL@allianz.com</p>  </body> </html>  ";

		String requestBody = "{\n    \"classId\": \"com.cislapi.coreinsurance.core.document.Email\",\n    \"message\": \""
				+ message + "\",\n    \"subject\": \"Game booking is Successful\",\n    \"to\": [\n        \""
				+ bookBySingleRequestDTO.getEmail()
				+ "\"\n    ],\n    \"from\": [\n        \"no-reply@allianz.com\"\n    ],\n    \"signature\": \"HTML\"\n}";

		RequestBody body = RequestBody.create(mediaType, requestBody);

		Request request = new Request.Builder()

				.url("https://ipc-africa-dev.sandbox0.aztecse-itmpproduct-sandbox.ec1.aws.aztec.cloud.allianz/eWS/sendEmail")

				.method("POST", body)

				.addHeader("Content-Type", "application/json")

				.build();
		try {
			Response response = client.newCall(request).execute();
			System.out.println("Email sent!!! " + bookBySingleRequestDTO.getEmail());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	public StandardApiResponseHandler deleteBooking(Integer bookingId) {
		BookBySingleRequest bookingById = bookBySingleRequestRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingID", bookingId));
		bookBySingleRequestRepo.delete(bookingById);
		StandardApiResponseHandler standardApiResponseHandler = new StandardApiResponseHandler("Deleted Successfully",
				true);
		return standardApiResponseHandler;
	}

	/*
	 * @Override public BookBySingleRequestDTO updateBooking(BookBySingleRequestDTO
	 * bookBySingleRequestDTO, Integer BookingId) { BookBySingleRequest foundBooking
	 * = bookBySingleRequestRepo.findById(BookingId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Booking", "Id", BookingId));
	 * foundBooking.setCity(bookBySingleRequestDTO.getCity());
	 * foundBooking.setAreaName(bookBySingleRequestDTO.getAreaName());
	 * foundBooking.setTower(bookBySingleRequestDTO.getTower());
	 * foundBooking.setFloor(bookBySingleRequestDTO.getFloor());
	 * foundBooking.setEmail(bookBySingleRequestDTO.getDate());
	 * foundBooking.setGame(bookBySingleRequestDTO.getGame());
	 * foundBooking.setLoginTime(bookBySingleRequestDTO.getLoginTime());
	 * foundBooking.setLogoutTime(bookBySingleRequestDTO.getLogoutTime());
	 * foundBooking.setPhase(bookBySingleRequestDTO.getPhase());
	 * 
	 * //city String city = bookBySingleRequestDTO.getCity(); CityDTO cityDTO = new
	 * CityDTO(); cityDTO.setCityName(city); CityDTO createdCityDTO =
	 * cityService.createCity(cityDTO); City createdCity =
	 * modelMapper.map(createdCityDTO, City.class); cityRepo.save(createdCity);
	 * 
	 * // area String areaName = bookBySingleRequestDTO.getAreaName(); AreaDTO
	 * areaDTO = new AreaDTO(); areaDTO.setAreaName(areaName); AreaDTO
	 * createdAreaDTO = areaService.createArea(areaDTO, createdCity.getId()); Area
	 * createdArea = modelMapper.map(createdAreaDTO, Area.class);
	 * areaRepo.save(createdArea);
	 * 
	 * // phase String phase = bookBySingleRequestDTO.getPhase(); PhaseDTO phaseDTO
	 * = new PhaseDTO(); phaseDTO.setPhaseName(phase); PhaseDTO createdPhaseDTO =
	 * phaseService.createPhase(phaseDTO, createdArea.getId()); Phase createdPhase =
	 * modelMapper.map(createdPhaseDTO, Phase.class); phaseRepo.save(createdPhase);
	 * 
	 * // towerfloor String tower = bookBySingleRequestDTO.getTower(); int floor =
	 * bookBySingleRequestDTO.getFloor(); TowerFloorDTO towerFloorDTO = new
	 * TowerFloorDTO(); towerFloorDTO.setTower(tower);
	 * towerFloorDTO.setFloor(floor); TowerFloorDTO createTowerFloorDTO =
	 * towerFloorService.createTowerFloor(towerFloorDTO, createdPhase.getPhaseId());
	 * TowerFloor createdTowerFloor = modelMapper.map(createTowerFloorDTO,
	 * TowerFloor.class); towerFloorRepo.save(createdTowerFloor);
	 * 
	 * // game String game = bookBySingleRequestDTO.getGame(); GameDTO gameDTO = new
	 * GameDTO(); gameDTO.setGameName(game); GameDTO createdGameDto =
	 * gameService.createGame(gameDTO); Game createdGame =
	 * modelMapper.map(createdGameDto, Game.class); gameRepo.save(createdGame);
	 * 
	 * return null; }
	 */

}
