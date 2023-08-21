package com.grooming.blog.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.BookingDTO;
import com.grooming.blog.serviceImpl.BookingServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

	@Autowired
	BookingServiceImpl bookingServiceImpl;

	@PostMapping("/createbooking")
	ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO,
			@RequestParam Integer TowerFloorId, @RequestParam Integer gameId) throws IOException {
		BookingDTO createdBooking = bookingServiceImpl.createBooking(bookingDTO, TowerFloorId, gameId);
		return new ResponseEntity<BookingDTO>(createdBooking, HttpStatus.OK);
	}

	@GetMapping("/getbookingbyId")
	ResponseEntity<BookingDTO> getBookingById(@RequestParam Integer Id) {
		BookingDTO bookingById = bookingServiceImpl.getBookingById(Id);
		return new ResponseEntity<BookingDTO>(bookingById, HttpStatus.OK);
	}

	@GetMapping("/getbooking")
	ResponseEntity<List<BookingDTO>> getallBooking() {
		List<BookingDTO> allBookings = bookingServiceImpl.getAllBookings();
		return new ResponseEntity<List<BookingDTO>>(allBookings, HttpStatus.OK);
	}

	@PutMapping("/updatebooking")
	ResponseEntity<BookingDTO> updateBooking(@PathVariable Integer Id, @Valid @RequestBody BookingDTO bookingDTO) {
		BookingDTO updatedBooking = bookingServiceImpl.updateBooking(bookingDTO, Id);
		return new ResponseEntity<BookingDTO>(updatedBooking, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	ResponseEntity<StandardApiResponseHandler> deleteBooking(@RequestParam Integer Id) {
		StandardApiResponseHandler deletedBooking = bookingServiceImpl.deleteBooking(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedBooking, HttpStatus.OK);
	}
}
