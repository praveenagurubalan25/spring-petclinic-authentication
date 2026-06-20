package org.springframework.samples.petclinic.security;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String petName;

	private String ownerName;

	private LocalDate visitDate;

	private String reasonForVisit;

	private String diagnosis;

	@Column(length = 1000)
	private String medicines;

	@Column(length = 1000)
	private String instructions;

	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "vet_id")
	private VetProfile vetProfile;

	public Prescription() {
		this.createdAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public String getReasonForVisit() {
		return reasonForVisit;
	}

	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public VetProfile getVetProfile() {
		return vetProfile;
	}

	public void setVetProfile(VetProfile vetProfile) {
		this.vetProfile = vetProfile;
	}

}
