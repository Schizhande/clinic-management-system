package com.myclinic.part2project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.Schedule;
import com.myclinic.part2project.service.ScheduleService;
import com.myclinic.part2project.service.StaffService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	StaffService staffService;

	@GetMapping(value = "/createSchedule")
	public String showScheduleForm(Model theModel) {
		Schedule schedule = new Schedule();
		theModel.addAttribute("schedule", schedule);
		theModel.addAttribute("doctors", staffService.getDoctors());
		return "createSchedule";
	}

	@PostMapping(value = "/saveSchedule")
	public String saveSchedule(@Valid @ModelAttribute("schedule") Schedule schedule,BindingResult result, Model theModel) {
		if(result.hasErrors()){
			theModel.addAttribute("doctors", staffService.getDoctors());
			return "createSchedule";
		}
		scheduleService.saveSchedule(schedule);
		return "redirect:/getDoctorSchedule";
	}

	@GetMapping(value = "/getDoctorSchedule")
	public String getDoctorSchedule(Model theModel) {
		List<Schedule> schedules = scheduleService.getSchedule();
		theModel.addAttribute("schedules", schedules);
		return "doctor-schedule";
	}

	@GetMapping(value="/deleteSchedule")
	public String deleteSchedule(@RequestParam("scheduleId")int scheduleID){
		scheduleService.deleteSchedule(scheduleID);
		return "redirect:/getDoctorSchedule";
	}
	
	@GetMapping(value="/updateSchedule")
	public String updateSchedule(@RequestParam("scheduleId")int scheduleID, Model theModel){
		Schedule schedule=scheduleService.getSchedule(scheduleID);
		theModel.addAttribute("schedule", schedule);
		theModel.addAttribute("doctors", staffService.getDoctors());
		return "createSchedule";
	}
}
