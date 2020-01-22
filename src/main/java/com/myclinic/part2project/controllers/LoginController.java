package com.myclinic.part2project.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.UserProfileService;
import com.myclinic.part2project.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userService;

	@Autowired
	PatientService patientService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/changePassword")
	public String changePassword(Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		theModel.addAttribute("user", user);
		return "changePassword";

	}

	@PostMapping(value = "/resetPassword")
	public String resetPassword(@Valid @ModelAttribute("user") User user, BindingResult result, Model theModel) {

		User u = userService.findById(user.getUserID());
	 
		if (!passwordEncoder.matches(user.getOldPassword(), u.getPassword())) {
			System.out.println(" >>>>>>>>>>> password does not match");
			FieldError ssoError = new FieldError("user", "oldPassword", messageSource.getMessage(
					"notMatch.user.oldPassword", new String[] { user.getOldPassword()}, Locale.getDefault()));
			result.addError(ssoError);
			return "changePassword";
		}else{
			if (result.hasErrors()) {
				System.out.println(">>>>>>>>>>> in has error");
				return "changePassword";
			}
			System.out.println(" >>>>>>>>>>> no error");
			userService.updateUser(user);
			theModel.addAttribute("resetSuccess", true);
			return "main";
		}

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
