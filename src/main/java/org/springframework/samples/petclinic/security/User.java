package org.springframework.samples.petclinic.security;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	private Integer id;

	private String username;

	private String password;

	private String email;

	private boolean enabled = true;

	private LocalDateTime createdAt;

	private LocalDateTime lastLogin;

	private Set<String> roles = new HashSet<>();

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(unique = true, nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Email
	@Column(unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	@Column(nullable = false)
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(final LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
		name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id")
	)
	@Column(name = "role")
	public Set<String> getRoles() {
		return this.roles;
	}

	public void setRoles(final Set<String> roles) {
		this.roles = roles;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}
