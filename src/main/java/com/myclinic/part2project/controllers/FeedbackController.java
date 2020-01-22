package com.myclinic.part2project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.Feedback;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.FeedbackService;
import com.myclinic.part2project.service.GenderService;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.UserService;

@Controller
public class FeedbackController {

	@Autowired
	PatientService patientService;

	@Autowired
	UserService userService;
	
	@Autowired
	FeedbackService feedbackService;

	@GetMapping(value="/addFeedback")
	public String postFeedback(Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		Feedback feedback =new Feedback();
		feedback.setPatient(patient);
		theModel.addAttribute("feedback",feedback);
		return "feedbackFrom";
	}
	
	@PostMapping(value="/saveFeedBack")
	public String saveFeedBack(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult result,Model theModel){
      		
		if(result.hasErrors()){
			return "feedbackFrom";
		}
		feedbackService.saveFeedback(feedback);
		theModel.addAttribute("feedbackSuccess",true);
		theModel.addAttribute("feedback",feedback);
		return "feedbackFrom";
	}

	@GetMapping(value="/deletePost")
	public String deletePost(@RequestParam("feedbackId")int feedbackId, Model theModel){
		feedbackService.deleteFeedback(feedbackId);
		theModel.addAttribute("deleteSuccess",true);
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		Feedback feedback =new Feedback();
		feedback.setPatient(patient);
		theModel.addAttribute("feedback",feedback);
		return "feedbackFrom";
		
	}
	
	@GetMapping(value="/viewFeedback")
	public String viewFeedback(Model theModel){
		List<Feedback> feedbacks= feedbackService.getFeeddbacks();
		theModel.addAttribute("feedbacks",feedbacks);
		return "list-feedbacks";
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
