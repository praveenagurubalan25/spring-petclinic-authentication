package org.springframework.samples.petclinic.security;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.samples.petclinic.owner.OwnerRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

	private final PrescriptionService prescriptionService;

	private final VetProfileService vetProfileService;
	private final OwnerRepository ownerRepository;

	public PrescriptionController(
		PrescriptionService prescriptionService,
		VetProfileService vetProfileService,
		OwnerRepository ownerRepository) {

		this.prescriptionService = prescriptionService;
		this.vetProfileService = vetProfileService;
		this.ownerRepository = ownerRepository;
	}

	@GetMapping("/create")
	public String createPrescriptionPage(Model model) {

		model.addAttribute("prescription",
			new Prescription());

		model.addAttribute("vets",
			vetProfileService.getAllVets());

		model.addAttribute("owners",
			ownerRepository.findAll());



		return "vet/create-prescription";
	}

	@PostMapping("/create")
	public String savePrescription(
		@ModelAttribute Prescription prescription,
		@RequestParam Integer vetId) {

		VetProfile vet =
			vetProfileService.getById(vetId);

		prescription.setVetProfile(vet);

		prescriptionService.save(prescription);

		return "redirect:/prescriptions/list";
	}

	@GetMapping("/list")
	public String listPrescriptions(Model model) {

		model.addAttribute("prescriptions",
			prescriptionService.getAll());

		return "vet/prescriptions";
	}

	@GetMapping("/owner")
	public String ownerPrescriptions(
		Authentication authentication,
		Model model) {

		String username =
			authentication.getName();

		model.addAttribute(
			"prescriptions",
			prescriptionService
				.getByOwnerName(username));

		return "owner/prescriptions";
	}

	@GetMapping("/my")
	public String myPrescriptions(Model model,
	                              Authentication authentication) {

		String username = authentication.getName();

		model.addAttribute(
			"prescriptions",
			prescriptionService.getByOwnerName(username)
		);

		return "user/prescriptions";
	}

	@GetMapping("/pets/{ownerId}")
	@ResponseBody
	public List<Pet> getPetsByOwner(@PathVariable Integer ownerId) {

		Owner owner = ownerRepository
			.findById(ownerId)
			.orElseThrow();

		return new ArrayList<>(owner.getPets());
	}

	@GetMapping("/pdf/{id}")
	public void downloadPdf(@PathVariable Integer id,
	                        HttpServletResponse response)
		throws Exception {

		Prescription prescription =
			prescriptionService.getById(id);

		response.setContentType("application/pdf");

		response.setHeader(
			"Content-Disposition",
			"attachment; filename=prescription_" +
				id + ".pdf");

		Document document = new Document();

		PdfWriter.getInstance(
			document,
			response.getOutputStream());

		document.open();

		document.add(
			new Paragraph("PET CLINIC PRESCRIPTION"));

		document.add(new Paragraph(" "));

		document.add(
			new Paragraph(
				"Pet Name : "
					+ prescription.getPetName()));

		document.add(
			new Paragraph(
				"Owner Name : "
					+ prescription.getOwnerName()));

		document.add(
			new Paragraph(
				"Visit Date : "
					+ prescription.getVisitDate()));

		document.add(
			new Paragraph(
				"Reason : "
					+ prescription.getReasonForVisit()));

		document.add(
			new Paragraph(
				"Diagnosis : "
					+ prescription.getDiagnosis()));

		document.add(
			new Paragraph(
				"Medicines : "
					+ prescription.getMedicines()));

		document.add(
			new Paragraph(
				"Instructions : "
					+ prescription.getInstructions()));

		if (prescription.getVetProfile() != null) {

			document.add(
				new Paragraph(
					"Veterinarian : "
						+ prescription
						.getVetProfile()
						.getFullName()));
		}

		document.close();
	}



}
