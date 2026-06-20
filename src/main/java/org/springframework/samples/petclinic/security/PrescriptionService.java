package org.springframework.samples.petclinic.security;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

	private final PrescriptionRepository repository;

	public PrescriptionService(PrescriptionRepository repository) {
		this.repository = repository;
	}

	public Prescription save(Prescription prescription) {
		return repository.save(prescription);
	}

	public List<Prescription> getAll() {
		return repository.findAll();
	}

	public List<Prescription> getByOwnerName(String ownerName) {

		return repository.findByOwnerName(ownerName);
	}

	public Prescription getById(Integer id) {

		return repository.findById(id)
			.orElseThrow(
				() -> new RuntimeException(
					"Prescription Not Found"));
	}

}
