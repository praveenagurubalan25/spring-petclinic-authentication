package org.springframework.samples.petclinic.security;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,
	                   PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (user.getCreatedAt() == null) {
			user.setCreatedAt(LocalDateTime.now());
		}

		return userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	public void disableUser(Integer id) {

		User user = userRepository.findById(id).orElseThrow();

		user.setEnabled(false);

		userRepository.save(user);
	}

	public void enableUser(Integer id) {

		User user = userRepository.findById(id).orElseThrow();

		user.setEnabled(true);

		userRepository.save(user);
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User not found"));
	}



	public User updateUser(User updatedUser) {

		User existingUser = userRepository.findById(updatedUser.getId())
			.orElseThrow(() -> new RuntimeException("User not found"));

		existingUser.setUsername(updatedUser.getUsername());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setEnabled(updatedUser.isEnabled());

		return userRepository.save(existingUser);
	}


}
