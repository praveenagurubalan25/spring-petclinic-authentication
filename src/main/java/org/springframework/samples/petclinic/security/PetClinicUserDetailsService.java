package org.springframework.samples.petclinic.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PetClinicUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public PetClinicUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		System.out.println("LOGIN USER : " + user.getUsername());
		System.out.println("ROLES FROM DB : " + user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true,
				user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

}
