package org.springframework.samples.petclinic.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VetProfileRepository
	extends JpaRepository<VetProfile, Integer> {

}
