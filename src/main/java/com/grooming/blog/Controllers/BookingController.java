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

import com.grooming.blog.DTO.BookingDTO;
import com.grooming.blog.serviceImpl.BookingServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/booking")
public class BookingController {

	@Autowired
	BookingServiceImpl bookingServiceImpl;

	@PostMapping("/createbooking/{TowerFloorId}/{gameId}")
	ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO,
			@PathVariable Integer TowerFloorId, @PathVariable Integer gameId) {
		BookingDTO createdBooking = bookingServiceImpl.createBooking(bookingDTO, TowerFloorId, gameId);
		return new ResponseEntity<BookingDTO>(createdBooking, HttpStatus.OK);
	}

	@GetMapping("/getbooking/{bookingId}")
	ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer bookingId) {
		BookingDTO bookingById = bookingServiceImpl.getBookingById(bookingId);
		return new ResponseEntity<BookingDTO>(bookingById, HttpStatus.OK);
	}

	@GetMapping("/getbooking")
	ResponseEntity<List<BookingDTO>> getallBooking() {
		List<BookingDTO> allBookings = bookingServiceImpl.getAllBookings();
		return new ResponseEntity<List<BookingDTO>>(allBookings, HttpStatus.OK);
	}

	@PutMapping("/updatebooking/{bookingId}")
	ResponseEntity<BookingDTO> updateBooking(@PathVariable Integer bookingId,
			@Valid @RequestBody BookingDTO bookingDTO) {
		BookingDTO updatedBooking = bookingServiceImpl.updateBooking(bookingDTO, bookingId);
		return new ResponseEntity<BookingDTO>(updatedBooking, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{bookingId}")
	ResponseEntity<StandardApiResponseHandler> deleteBooking(@PathVariable Integer bookingId) {
		StandardApiResponseHandler deletedBooking = bookingServiceImpl.deleteBooking(bookingId);
		return new ResponseEntity<StandardApiResponseHandler>(deletedBooking, HttpStatus.OK);
	}
}
