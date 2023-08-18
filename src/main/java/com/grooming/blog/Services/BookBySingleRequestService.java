package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.BookBySingleRequestDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface BookBySingleRequestService {
	BookBySingleRequestDTO createBooking(BookBySingleRequestDTO bookBySingleRequestDTO);

	BookBySingleRequestDTO getBookingById(Integer Id);

	List<BookBySingleRequestDTO> getAllBookings();

	// BookBySingleRequestDTO updateBooking(BookBySingleRequestDTO
	// bookBySingleRequestDTO, Integer BookingId);

	StandardApiResponseHandler deleteBooking(Integer bookingId);
}
