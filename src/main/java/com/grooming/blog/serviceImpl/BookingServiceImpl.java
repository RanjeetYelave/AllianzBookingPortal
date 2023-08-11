package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.BookingDTO;
import com.grooming.blog.Entity.Booking;
import com.grooming.blog.Entity.TowerFloor;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.BookingRepo;
import com.grooming.blog.Repo.TowerFloorRepo;
import com.grooming.blog.Services.BookingService;
import com.grooming.blog.utils.StandardApiResponseHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@Override
	public BookingDTO createBooking(BookingDTO bookingDTO, Integer ToweFloorId) {
		TowerFloor foundTowerFloor = towerFloorRepo.findById(ToweFloorId)
				.orElseThrow(() -> new ResourceNotFoundException("TowerFloor", "TowerfloorId", ToweFloorId));
		Booking booking = modelMapper.map(bookingDTO, Booking.class);
		booking.setTowerFloor(foundTowerFloor);
		Booking savedBooking = bookingRepo.save(booking);
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
