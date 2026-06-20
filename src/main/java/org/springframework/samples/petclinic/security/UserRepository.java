package org.springframework.samples.petclinic.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

	List<User> findByEnabled(boolean enabled);

}
