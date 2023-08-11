package com.grooming.blog.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.BookingDTO;
import com.grooming.blog.Entity.Booking;
import com.grooming.blog.Entity.Game;
import com.grooming.blog.Entity.TowerFloor;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.BookingRepo;
import com.grooming.blog.Repo.GameRepo;
import com.grooming.blog.Repo.TowerFloorRepo;
import com.grooming.blog.Services.BookingService;
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
public class BookingServiceImpl implements BookingService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	TowerFloorRepo towerFloorRepo;
	@Autowired
	GameRepo gameRepo;

	@Override
	public BookingDTO createBooking(BookingDTO bookingDTO, Integer ToweFloorId, Integer gameId) throws IOException {
		TowerFloor foundTowerFloor = towerFloorRepo.findById(ToweFloorId)
				.orElseThrow(() -> new ResourceNotFoundException("TowerFloor", "TowerfloorId", ToweFloorId));

		Game foundGame = gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game", "GameId", gameId));

		Booking booking = modelMapper.map(bookingDTO, Booking.class);
		booking.setGame(foundGame);
		booking.setTowerFloor(foundTowerFloor);
		Booking savedBooking = bookingRepo.save(booking);
		// Email Response Configuration
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");

		RequestBody body = RequestBody.create(mediaType,
				"{\n    \"classId\": \"com.cislapi.coreinsurance.core.document.Email\",\n    \"message\": \"Booking Confirmed from Time"
						+ bookingDTO.getFromTime() + " to " + bookingDTO.getToTime()
						+ "\",\n    \"subject\": \"Booking Confirmation for " + foundGame.getGameName()
						+ "\",\n    \"to\": [\n        \"" + bookingDTO.getEmailId()
						+ "\n    ],\n    \"from\": [\n        \"no-reply@allianz.com\"\n    ],\n    \"signature\": \"Allianz Game Booking Portal - no-reply\"\n}");
		Request request = new Request.Builder().url(
				"https://ipc-africa-dev.sandbox0.aztecse-itmpproduct-sandbox.ec1.aws.aztec.cloud.allianz/eWS/sendEmail")
				.method("POST", body).addHeader("Content-Type", "application/json").build();

		Response response = client.newCall(request).execute();

		return modelMapper.map(savedBooking, BookingDTO.class);
	}

	@Override
	public BookingDTO updateBooking(BookingDTO bookingDTO, Integer bookingId) {
		Booking existingBooking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingId", bookingId));
		existingBooking.setToTime(bookingDTO.getToTime());
		existingBooking.setFromTime(bookingDTO.getFromTime());
		existingBooking.setDate(bookingDTO.getDate());
		existingBooking.setEmailId(bookingDTO.getEmailId());
		Booking savedBooking = bookingRepo.save(existingBooking);
		return modelMapper.map(savedBooking, BookingDTO.class);
	}

	@Override
	public BookingDTO getBookingById(Integer bookingId) {
		Booking foundBooking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingId", bookingId));
		return modelMapper.map(foundBooking, BookingDTO.class);
	}

	@Override
	public List<BookingDTO> getAllBookings() {
		List<Booking> allBookings = bookingRepo.findAll();
		List<BookingDTO> allBookingDTOs = allBookings.stream()
				.map(eachBooking -> modelMapper.map(eachBooking, BookingDTO.class)).collect(Collectors.toList());
		return allBookingDTOs;
	}

	@Override
	public StandardApiResponseHandler deleteBooking(Integer bookingId) {
		Booking foundBooking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingId", bookingId));
		bookingRepo.delete(foundBooking);
		return new StandardApiResponseHandler("Booking Deleted Successfully", true);
	}

}
