package org.springframework.samples.petclinic.security;

import jakarta.persistence.*;

@Entity
@Table(name = "vet_profiles")
public class VetProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String fullName;

	private String specialization;

	private String qualification;

	private String phone;

	private String email;

	private Integer experienceYears;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public VetProfile() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
