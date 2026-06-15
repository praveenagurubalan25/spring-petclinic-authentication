package org.springframework.samples.petclinic.security;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePasswordController {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public ChangePasswordController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/change-password")
	public String showChangePasswordForm(Model model) {

		model.addAttribute("changePasswordDTO", new ChangePasswordDTO());

		return "change-password";
	}

	@PostMapping("/change-password")
	public String changePassword(@Valid ChangePasswordDTO changePasswordDTO, BindingResult result,
			Authentication authentication, Model model) {

		if (result.hasErrors()) {
			return "change-password";
		}

		User user = userRepository.findByUsername(authentication.getName())
			.orElseThrow(() -> new RuntimeException("User not found"));

		// Check current password
		if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {

			model.addAttribute("error", "Current password is incorrect");

			return "change-password";
		}

		// Check new password match
		if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {

			model.addAttribute("error", "New password and confirm password do not match");

			return "change-password";
		}

		// Update password
		user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));

		userRepository.save(user);

		model.addAttribute("success", "Password changed successfully");

		return "change-password";
	}

}
