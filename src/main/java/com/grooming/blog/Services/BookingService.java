package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.BookingDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface BookingService {
	BookingDTO createBooking(BookingDTO bookingDTO, Integer ToweFloorId, Integer gameId);

	BookingDTO updateBooking(BookingDTO bookingDTO, Integer bookingId);

	BookingDTO getBookingById(Integer bookingId);

	List<BookingDTO> getAllBookings();

	StandardApiResponseHandler deleteBooking(Integer bookingId);
}
