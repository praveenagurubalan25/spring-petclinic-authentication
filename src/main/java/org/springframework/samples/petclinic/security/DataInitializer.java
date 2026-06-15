package org.springframework.samples.petclinic.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {

		// 👉 Create ADMIN if not exists
		if (userRepository.findByUsername("admin").isEmpty()) {

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setEmail("admin@petclinic.com");
			admin.setEnabled(true);

			admin.getRoles().add("ROLE_ADMIN");

			userRepository.save(admin);
		}

		// 👉 Create NORMAL USER if not exists
		if (userRepository.findByUsername("user").isEmpty()) {

			User user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("user123"));
			user.setEmail("user@petclinic.com");
			user.setEnabled(true);

			user.getRoles().add("ROLE_USER");

			userRepository.save(user);
		}
		System.out.println("🔥 DataInitializer started");
	}

}
