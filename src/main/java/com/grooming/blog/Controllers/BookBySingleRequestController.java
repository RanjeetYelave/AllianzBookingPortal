package com.grooming.blog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.BookBySingleRequestDTO;
import com.grooming.blog.serviceImpl.BookBySingleRequestServiceImpl;

@RequestMapping("api/bookbysinglerequest")
@RestController
public class BookBySingleRequestController {
	@Autowired
	BookBySingleRequestServiceImpl bookBySingleRequestServiceImpl;

	@PostMapping("/createbooking")
	ResponseEntity<BookBySingleRequestDTO> createBooking(BookBySingleRequestDTO bookBySingleRequestDTO) {
		BookBySingleRequestDTO createdBooking = bookBySingleRequestServiceImpl.createBooking(bookBySingleRequestDTO);
		return new ResponseEntity<BookBySingleRequestDTO>(createdBooking, HttpStatus.OK);
	}
}
