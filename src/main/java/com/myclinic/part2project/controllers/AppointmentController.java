package com.myclinic.part2project.controllers;

import java.time.LocalDate;
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

import com.myclinic.part2project.helpClass.AppointDateFactory;
import com.myclinic.part2project.model.Appointment;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Schedule;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.AppointmentService;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.ScheduleService;
import com.myclinic.part2project.service.StaffService;
import com.myclinic.part2project.service.UserService;

@Controller
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	UserService userService;

	@Autowired
	PatientService patientService;

	@Autowired
	StaffService staffService;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	AppointDateFactory appointDateFactory;

	@GetMapping(value = "/appointmentForm")
	public String showAppointmentForm(@RequestParam("scheduleId") int scheduleId,
			@RequestParam("doctorId") int doctorId, Model theModel) {
		Appointment appoint = new Appointment();
		Schedule schedule = scheduleService.getSchedule(scheduleId);
		LocalDate tempDate = appointDateFactory.getAppointmentDate(LocalDate.now(), schedule.getDay());
		List<Appointment> appointments = appointmentService.getDoctorAppointmentPerDay(tempDate, doctorId);
		this.determineDate(tempDate, appointments, schedule, doctorId, appoint);
		appoint.setAppointmentTime(schedule.getStartTime());
		Staff doctor = staffService.getStaff(doctorId);
		appoint.setSchedule(schedule);

		appoint.setDoctor(doctor);
		theModel.addAttribute("appointment", appoint);
		return "bookAppointment";
	}

	@PostMapping(value = "/saveAppointment")
	public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment appointment, BindingResult result,
			Model theModel) {

		if (result.hasErrors()) {
			return "bookAppointment";
		}
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		appointment.setPatient(patient);
		appointmentService.saveAppointment(appointment);
		theModel.addAttribute("name", patient.getFirstName());
		theModel.addAttribute("appointment", appointment);
		theModel.addAttribute("appointmentSuccess", true);
		return "appointmentSuccess";
	}

	@GetMapping(value = "/listAppointment")
	public String listAppointment(Model theModel) {
		List<Appointment> appointments = appointmentService.getAppointments();
		theModel.addAttribute("appointment", appointments);
		return "list-appointment";
	}

	@GetMapping(value = "/acceptAppointment")
	public String acceptAppointment(@RequestParam("appointmentId") int appointID,Model theModel) {
		appointmentService.acceptAppointment(appointID);
		Appointment appoint = appointmentService.getAppointmen(appointID);
		theModel.addAttribute("appointment", appoint);
		return "viewAppointment";
	}

	@GetMapping(value = "/declineAppointment")
	public String declineAppointment(@RequestParam("appointmentId") int appointID,Model theModel) {
		appointmentService.declineAppointment(appointID);
		Appointment appoint = appointmentService.getAppointmen(appointID);
		theModel.addAttribute("appointment", appoint);
		return "viewAppointment";
	}

	@GetMapping(value = "/checkAppointmentStatus")
	public String checkAppointmentStatus(@RequestParam("scheduleId") int scheduleId, Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		Appointment appointment = appointmentService.getAppointment(patient.getPatientID(), scheduleId);
		theModel.addAttribute("name", patient.getFirstName());
		theModel.addAttribute("appointment", appointment);
		return "appointmentSuccess";
	}

	@GetMapping(value = "/editAppointment")
	public String editAppointment(@RequestParam("appointmentId") int appointId, Model theModel) {
		Appointment appoint = appointmentService.getAppointmen(appointId);
		theModel.addAttribute("appointment", appoint);
		theModel.addAttribute("edit", true);
		return "bookAppointment";
	}

	@PostMapping(value = "/updateAppointment")
	public String updateAppointment(@ModelAttribute("appointment") Appointment appoint, Model theModel) {
		appointmentService.editAppointment(appoint);
		theModel.addAttribute("appointment", appoint);
		theModel.addAttribute("editSuccess", true);
		return "appointmentSuccess";
	}

	@GetMapping(value = "/cancelAppointment")
	public String cancelAppointment(@RequestParam("appointmentId") int appointmentId, Model theModel) {
		appointmentService.deleteAppointment(appointmentId);
		List<Schedule> schedules = scheduleService.getSchedule();
		theModel.addAttribute("deleteSuccess", true);
		theModel.addAttribute("schedules", schedules);
		return "doctor-schedule";
	}

	@GetMapping(value = "/deleteAppointment")
	public String deleteAppointment(@RequestParam("appointmentId") int appointmentId, Model theModel) {
		appointmentService.deleteAppointment(appointmentId);
		List<Appointment> appointments = appointmentService.getAppointments();
		theModel.addAttribute("deleteSuccess", true);
		theModel.addAttribute("appointment", appointments);
		return "list-appointment";
	}
	@GetMapping(value = "/appointForm")
	public String appointForm() {
		return "appointForm";
	}

	@GetMapping(value = "/appointmentListPerDoctor")
	public String appointmentListPerDoctor(Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		Staff doctor = staffService.getStaffByUserId(user.getUserID());
		List<Appointment> appointments = appointmentService.appointmentListPerDoctor(doctor.getStaffID());
		theModel.addAttribute("appointment", appointments);
		return "list-appointment";
	}

	@GetMapping(value = "/viewAppointment")
	public String viewAppointment(@RequestParam("appointmentId") int appointId, Model theModel) {
		Appointment appoint = appointmentService.getAppointmen(appointId);
		theModel.addAttribute("appointment", appoint);
		return "viewAppointment";
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

	public void determineDate(LocalDate tempDate, List<Appointment> appointments, Schedule schedule, int doctorId,
			Appointment app) {
		LocalDate bookedDate = null;
		if (!appointments.isEmpty()) {
			if (appointments.size() >10) {
				bookedDate = appointments.get(0).getAppointmentDate();
				tempDate = appointDateFactory.getAppointmentDate(bookedDate, schedule.getDay());
				appointments = appointmentService.getDoctorAppointmentPerDay(tempDate, doctorId);
				determineDate(tempDate, appointments, schedule, doctorId, app);
			} else {
				app.setAppointmentDate(tempDate);
			}
		} else {
			app.setAppointmentDate(tempDate);
		}

	}
}
