package com.grooming.blog.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TowerFloor {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private int Tower;
	private String Floor;
	@ManyToOne
	private Phase phase;
	@OneToOne(mappedBy = "towerFloor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Booking booking;
}
