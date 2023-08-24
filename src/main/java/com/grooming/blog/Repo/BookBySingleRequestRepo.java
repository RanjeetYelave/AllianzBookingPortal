package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grooming.blog.Entity.BookBySingleRequest;

public interface BookBySingleRequestRepo extends JpaRepository<BookBySingleRequest, Integer> {
	// <Optional> BookBySingleRequestDTO findByDate(String Date);
}
