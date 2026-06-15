package org.springframework.samples.petclinic.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

	@GetMapping("/admin/test")
	public String adminTest() {
		return "Admin Access Granted";
	}

	@GetMapping("/user/test")
	public String userTest() {
		return "User Access Granted";
	}

}
