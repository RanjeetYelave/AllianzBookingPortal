package com.grooming.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grooming.blog.Entity.Phase;

public interface PhaseRepo extends JpaRepository<Phase, Integer> {

}
