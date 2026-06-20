package org.springframework.samples.petclinic.security;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VetProfileService {

	private final VetProfileRepository repository;

	public VetProfileService(VetProfileRepository repository) {
		this.repository = repository;
	}

	public VetProfile save(VetProfile vet) {
		return repository.save(vet);
	}

	public List<VetProfile> getAllVets() {
		return repository.findAll();
	}

	public VetProfile getById(Integer id) {

		return repository.findById(id)
			.orElseThrow(() ->
				new RuntimeException("Vet not found"));
	}

}
