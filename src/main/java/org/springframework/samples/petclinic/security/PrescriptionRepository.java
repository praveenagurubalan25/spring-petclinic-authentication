package org.springframework.samples.petclinic.security;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository
	extends JpaRepository<Prescription,Integer> {

	List<Prescription> findByOwnerName(String ownerName);

}
