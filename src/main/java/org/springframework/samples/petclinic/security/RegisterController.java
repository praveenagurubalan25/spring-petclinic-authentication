package org.springframework.samples.petclinic.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "security/register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setEnabled(true);

		user.getRoles().add("ROLE_USER");

		userRepository.save(user);

		return "redirect:/login";
	}

}
