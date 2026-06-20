package org.springframework.samples.petclinic.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	private final VetProfileService vetProfileService;

	private final PrescriptionService prescriptionService;

	public AdminController(
		UserService userService,
		VetProfileService vetProfileService,
		PrescriptionService prescriptionService) {

		this.userService = userService;
		this.vetProfileService = vetProfileService;
		this.prescriptionService = prescriptionService;
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}

	@GetMapping("/create-owner")
	public String createOwner() {
		return "admin/create-owner";
	}

	@GetMapping("/manage-users")
	public String manageUsers(Model model) {

		model.addAttribute("users", userService.getAllUsers());

		return "admin/manage-users";
	}

	@GetMapping("/disable-user/{id}")
	public String disableUser(@PathVariable Integer id) {

		userService.disableUser(id);

		return "redirect:/admin/manage-users";
	}

	@GetMapping("/enable-user/{id}")
	public String enableUser(@PathVariable Integer id) {

		userService.enableUser(id);

		return "redirect:/admin/manage-users";
	}

	@GetMapping("/delete-user/{id}")
	public String deleteUser(@PathVariable Integer id) {

		userService.deleteUser(id);

		return "redirect:/admin/manage-users";
	}

	@GetMapping("/edit-user/{id}")
	public String editUser(@PathVariable Integer id, Model model) {

		User user = userService.getUserById(id);

		model.addAttribute("user", user);

		return "admin/edit-user";
	}




	@PostMapping("/users/update")
	public String updateUser(@ModelAttribute User user) {

		userService.updateUser(user);

		return "redirect:/admin/manage-users";
	}

	@GetMapping("/create-user")
	public String createUserPage(Model model) {

		model.addAttribute("user", new User());

		return "admin/create-user";
	}
	@PostMapping("/create-user")
	public String saveUser(@ModelAttribute User user,
	                       @RequestParam("role") String role) {

		user.getRoles().add(role);

		userService.saveUser(user);

		if ("ROLE_OWNER".equals(role)) {
			return "redirect:/owners/new";
		}

		if ("ROLE_VET".equals(role)) {
			return "redirect:/admin/create-vet";
		}

		return "redirect:/admin/manage-users";
	}

	@GetMapping("/create-vet")
	public String createVetPage(Model model) {

		model.addAttribute("vet", new VetProfile());

		model.addAttribute("users",
			userService.getAllUsers());

		return "admin/create-vet";
	}

	@PostMapping("/create-vet")
	public String saveVet(@ModelAttribute VetProfile vet,
	                      @RequestParam Integer userId) {

		User user = userService.getUserById(userId);

		vet.setUser(user);

		vetProfileService.save(vet);

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/prescriptions")
	public String viewPrescriptions(Model model) {

		model.addAttribute(
			"prescriptions",
			prescriptionService.getAll());

		return "admin/prescriptions";
	}

}
