package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grooming.blog.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
