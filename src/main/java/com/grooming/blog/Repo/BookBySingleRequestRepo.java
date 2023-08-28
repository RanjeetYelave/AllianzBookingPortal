package com.grooming.blog.Repo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grooming.blog.Entity.BookBySingleRequest;

@Repository
public interface BookBySingleRequestRepo extends JpaRepository<BookBySingleRequest, Integer> {
	@Query(value = "SELECT COUNT(*) > 0 " + "FROM book_by_single_request " + "WHERE date = :date "
			+ "AND login_time <= :logoutTime " + "AND logout_time >= :loginTime", nativeQuery = true)
	long doesBookBySingleRequestExistInDateTimeRange(LocalDate date, LocalTime loginTime, LocalTime logoutTime);
}
