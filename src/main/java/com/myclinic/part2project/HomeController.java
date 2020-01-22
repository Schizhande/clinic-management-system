package com.myclinic.part2project;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclinic.part2project.charts.services.CanvasjsChartService;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.model.UserProfile;
import com.myclinic.part2project.service.AppointmentService;
import com.myclinic.part2project.service.FeedbackService;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.StaffService;
import com.myclinic.part2project.service.UserProfileService;
import com.myclinic.part2project.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserService userService;

	@Autowired
	StaffService staffService;

	@Autowired
	private CanvasjsChartService canvasjsChartService;

	@Autowired
	PatientService patientService;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	FeedbackService feedbackService;
	

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		if (role.contains("PATIENT")) {
			User user = userService.findByUsername(getPrincipal());
			Patient patient = patientService.findByUserId(user.getUserID());
			model.addAttribute("patient", patient);
			System.out.println(">>>>>>>>>>>>patient");
			return "main";
		} else if (role.contains("NURSE") || role.contains("DOCTOR")
				|| (role.contains("RECEPTIONIST") || (role.contains("PHARMACIST")))) {
			User u = userService.findByUsername(getPrincipal());
			Staff staff = staffService.getStaffByUserId(u.getUserID());
			model.addAttribute("staff", staff);
			System.out.println(">>>>>>>>>>>>staff");
			return "main";
		}else{

			List<Patient> patients = patientService.getPatients();
			List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
			Integer appoints= appointmentService.getAppointments().size();
			Integer pats= patientService.getPatients().size();
			Integer fbs=feedbackService.getFeeddbacks().size();
			Integer stfs=staffService.getStaffs().size();
			model.addAttribute("pats",pats);
			model.addAttribute("feedbacks", fbs);
			model.addAttribute("appointments",appoints);
			model.addAttribute("staffs",stfs);
			model.addAttribute("dataPointsList", canvasjsDataList);
			model.addAttribute("patients", patients);
			return "main";
		}
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "fancy-login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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
