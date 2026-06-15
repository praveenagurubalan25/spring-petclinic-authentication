package org.springframework.samples.petclinic.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		String role = authentication.getAuthorities().iterator().next().getAuthority();

		System.out.println("LOGIN ROLE: " + role); // debug

		if (role.equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin/dashboard");
			return;
		}

		if (role.equals("ROLE_USER")) {
			response.sendRedirect("/user/dashboard");
			return;
		}

		response.sendRedirect("/welcome");
	}

}
