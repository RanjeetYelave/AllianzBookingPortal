package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.grooming.blog.Entity.Area;

@EnableJpaRepositories
public interface AreaRepo extends JpaRepository<Area, Integer> {

	// List<Area> findbyCity(City city);

}
