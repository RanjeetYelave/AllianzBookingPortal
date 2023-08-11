package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grooming.blog.Entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

}
