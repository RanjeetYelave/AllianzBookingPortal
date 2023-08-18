package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.BookBySingleRequestDTO;

public interface BookBySingleRequestService {
	BookBySingleRequestDTO createBooking(BookBySingleRequestDTO bookBySingleRequestDTO);

	BookBySingleRequestDTO getBookingById(Integer Id);

	List<BookBySingleRequestDTO> getAllBookings();

}
