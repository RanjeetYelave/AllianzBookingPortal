package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grooming.blog.Entity.Game;

public interface GameRepo extends JpaRepository<Game, Integer> {

}
