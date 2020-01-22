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

import com.myclinic.part2project.model.Specialty;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.model.UserProfile;
import com.myclinic.part2project.service.GenderService;
import com.myclinic.part2project.service.JobTitleService;
import com.myclinic.part2project.service.SpecialtyService;
import com.myclinic.part2project.service.StaffService;
import com.myclinic.part2project.service.UserProfileService;
import com.myclinic.part2project.service.UserService;

@Controller
public class StaffController {

	@Autowired
	StaffService staffService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserService userService;

	@Autowired
	SpecialtyService specialtyService;

	@Autowired
	GenderService genderMaritalService;

	@Autowired
	JobTitleService jobTitleService;

	@Autowired
	MessageSource messageSource;

	@GetMapping(value = "/staffForm")
	public String showPatientForm(Model theModel) {
		List<Specialty> specialty = specialtyService.getSpecialty();
		Staff staff = new Staff();
		theModel.addAttribute("genders", genderMaritalService.getGenders());
		theModel.addAttribute("maritals", genderMaritalService.getMaritals());
		theModel.addAttribute("jobTitles", jobTitleService.getJobTitles());
		theModel.addAttribute("specialties", specialty);
		theModel.addAttribute("staff", staff);
		return "staffForm";
	}

	@PostMapping(value = "/saveOrUpdateStaff")
	public String savePatient(@Valid @ModelAttribute("staff") Staff staff, BindingResult result, Model theModel) {
		if (staff.getStaffID() == 0) {
			if (!userService.isUserUsernameUnique(staff.getUser().getUserID(), staff.getUser().getUsername())) {
				FieldError ssoError = new FieldError("staff", "user.username",
						messageSource.getMessage("non.unique.user.username",
								new String[] { staff.getUser().getUsername() }, Locale.getDefault()));
				result.addError(ssoError);
				theModel.addAttribute("genders", genderMaritalService.getGenders());
				theModel.addAttribute("maritals", genderMaritalService.getMaritals());
				return "staffForm";
			}
		}

		if (result.hasErrors()) {
			theModel.addAttribute("genders", genderMaritalService.getGenders());
			theModel.addAttribute("maritals", genderMaritalService.getMaritals());
			theModel.addAttribute("jobTitles", jobTitleService.getJobTitles());
			theModel.addAttribute("specialties", specialtyService.getSpecialty());
			return "staffForm";
		}
		staffService.saveStaff(staff);
		theModel.addAttribute("staff",staff);
		theModel.addAttribute("saveSuccess", true);
		return "staffProfile";
	}

	@GetMapping(value = { "/listStaffs" })
	public String listStaffs(Model theModel) {
		List<Staff> staffs = staffService.getStaffs();
		theModel.addAttribute("staffs", staffs);
		return "list-staffs";
	}

	@GetMapping("/showFormForStaffUpdate")
	public String showFormForStaffUpdate(@RequestParam("staffId") int staffId, Model theModel) {
		List<Specialty> specialty = specialtyService.getSpecialty();
		theModel.addAttribute("specialties", specialty);
		Staff staff = staffService.getStaff(staffId);
		theModel.addAttribute("jobTitles", jobTitleService.getJobTitles());
		theModel.addAttribute("genders", genderMaritalService.getGenders());
		theModel.addAttribute("maritals", genderMaritalService.getMaritals());
		theModel.addAttribute("edit", true);
		theModel.addAttribute("staff", staff);
		return "staffForm";
	}

	@GetMapping("/deleteStaff")
	public String deleteCustomer(@RequestParam("staffId") int staffId) {
		staffService.deleteStaff(staffId);
		return "redirect:/listStaffs";
	}

	@GetMapping(value = "/viewStaffProfile")
	private String viewStaffProfile(Model theModel) {
		User u = userService.findByUsername(getPrincipal());
		Staff staff = staffService.getStaffByUserId(u.getUserID());
		theModel.addAttribute("staff", staff);
		return "staffProfile";
	}

	@GetMapping(value = "/editStaffProfile")
	public String editStaffProfile(Model theModel) {
		User u = userService.findByUsername(getPrincipal());
		Staff staff = staffService.getStaffByUserId(u.getUserID());
		theModel.addAttribute("genders", genderMaritalService.getGenders());
		theModel.addAttribute("maritals", genderMaritalService.getMaritals());
		theModel.addAttribute("jobTitles", jobTitleService.getJobTitles());
		theModel.addAttribute("staff", staff);
		return "staffForm";
	}
	@GetMapping(value = "/viewStaff")
	private String viewProfile(@RequestParam("staffId") int staffId, Model theModel) {
		Staff staff = staffService.getStaff(staffId);
		theModel.addAttribute("staff", staff);
		return "staffProfile";
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

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

}
