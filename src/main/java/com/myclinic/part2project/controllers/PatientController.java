package com.myclinic.part2project.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.GenderService;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.UserService;

@Controller
public class PatientController {

	@Autowired
	PatientService patientService;

	@Autowired
	GenderService genderMaritalService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/patientForm")
	public String showPatientForm(Model theModel) {
		Patient patient = new Patient();
		theModel.addAttribute("genders", genderMaritalService.getGenders());
		theModel.addAttribute("maritals", genderMaritalService.getMaritals());
		theModel.addAttribute("patient", patient);
		return "patientForm";
	}

	@GetMapping(value = "/patientSearch")
	public String showPatientSearchForm() {
		return "patientSearch";
	}

	@GetMapping(value = "/searchPatient")
	public String searchPatient(@RequestParam("patientID") int patientId, Model theModel) {
		Patient patient = patientService.getPatient(patientId);
		if (patient == null) {
			theModel.addAttribute("notFound", true);
		}
		theModel.addAttribute("patient", patient);
		return "patientProfile";
	}

	@PostMapping(value = "/saveOrUpdatePatient")
	public String savePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model theModel) {
		if (patient.getPatientID() == 0) {
			if (!userService.isUserUsernameUnique(patient.getUser().getUserID(), patient.getUser().getUsername())) {
				FieldError ssoError = new FieldError("patient", "user.username",
						messageSource.getMessage("non.unique.user.username",
								new String[] { patient.getUser().getUsername() }, Locale.getDefault()));
				result.addError(ssoError);
				theModel.addAttribute("genders", genderMaritalService.getGenders());
				theModel.addAttribute("maritals", genderMaritalService.getMaritals());
				return "patientForm";
			}
		}

		if (result.hasErrors()) {
			theModel.addAttribute("genders", genderMaritalService.getGenders());
			theModel.addAttribute("maritals", genderMaritalService.getMaritals());
			return "patientForm";
		}
		patientService.savePatient(patient);
		theModel.addAttribute("patient",patient);
		theModel.addAttribute("saveSuccess", true);
		return "patientProfile";
	}

	@GetMapping(value = { "/listPatients" })
	public String listPatients(Model theModel) {
		List<Patient> patients = patientService.getPatients();
		theModel.addAttribute("patients", patients);
		return "list-patients";
	}

	@GetMapping("/showFormForPatientUpdate")
	public String showFormForPatientUpdate(@RequestParam("patientId") int patientId, Model theModel) {
		Patient patient = patientService.getPatient(patientId);
		theModel.addAttribute("patient", patient);
		theModel.addAttribute("genders", genderMaritalService.getGenders());
		theModel.addAttribute("maritals", genderMaritalService.getMaritals());
		theModel.addAttribute("edit", true);
		return "patientForm";
	}

	@GetMapping("/deletePatient")
	public String deleteCustomer(@RequestParam("patientId") int patientId) {

		patientService.deletePatient(patientId);
		return "redirect:/listPatients";
	}

	@GetMapping("/viewPatientProfile")
	public String viewPatient(@RequestParam("patientId") Integer patientId, Model theModel) {
		if (patientId == null) {
			User user = userService.findByUsername(getPrincipal());
			Patient patient = patientService.findByUserId(user.getUserID());
		}
		Patient patient = patientService.getPatient(patientId);
		theModel.addAttribute("patient", patient);
		return "patientProfile";
	}

	@GetMapping("/viewPatProfile")
	public String viewProfile(Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		theModel.addAttribute("patient", patient);
		return "patientProfile";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
