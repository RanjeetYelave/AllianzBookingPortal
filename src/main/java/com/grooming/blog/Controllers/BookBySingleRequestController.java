package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.BookBySingleRequestDTO;
import com.grooming.blog.serviceImpl.BookBySingleRequestServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

@RestController
@RequestMapping("api/bookbysinglerequest")
@CrossOrigin(origins = "http://10.174.0.188:4200")
public class BookBySingleRequestController {
	@Autowired
	BookBySingleRequestServiceImpl bookBySingleRequestServiceImpl;

	@PostMapping("/createbooking")
	@CrossOrigin(origins = "http://10.174.0.188:4200")
	ResponseEntity<BookBySingleRequestDTO> createBooking(@RequestBody BookBySingleRequestDTO bookBySingleRequestDTO) {
		BookBySingleRequestDTO createdBooking = bookBySingleRequestServiceImpl.createBooking(bookBySingleRequestDTO);
		return new ResponseEntity<BookBySingleRequestDTO>(createdBooking, HttpStatus.OK);
	}

	@GetMapping("/getbookingbyId")
	ResponseEntity<BookBySingleRequestDTO> getBookingById(@RequestParam Integer Id) {
		BookBySingleRequestDTO bookingById = bookBySingleRequestServiceImpl.getBookingById(Id);
		return new ResponseEntity<BookBySingleRequestDTO>(bookingById, HttpStatus.OK);
	}

	@GetMapping("/getbooking")
	ResponseEntity<List<BookBySingleRequestDTO>> getAllBooking() {
		List<BookBySingleRequestDTO> allBookings = bookBySingleRequestServiceImpl.getAllBookings();
		return new ResponseEntity<List<BookBySingleRequestDTO>>(allBookings, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{Id}")
	ResponseEntity<StandardApiResponseHandler> deleteBooking(@PathVariable Integer Id) {
		StandardApiResponseHandler deletedBookingResponse = bookBySingleRequestServiceImpl.deleteBooking(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedBookingResponse, HttpStatus.OK);
	}
}
