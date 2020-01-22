package com.myclinic.part2project.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.Comment;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.UserService;

@Controller
public class RecordController {

	@Autowired
	RecordService recordService;

	@Autowired
	UserService userService;

	@Autowired
	PatientService patientService;

	private Logger myLogger = Logger.getLogger(this.getClass().getName());

	@GetMapping(value = "/viewPatientPreviousRecord")
	public String viewPatientRecord(@RequestParam("patientId") int patientId, Model theModel) {
		List<Record> patientRecords = recordService.getPreviousRecord(patientId);
		String patientName = null;
		if (!patientRecords.isEmpty()) {
			patientName = patientRecords.get(0).getPatient().getLastName() + " "
					+ patientRecords.get(0).getPatient().getFirstName();
		}
		theModel.addAttribute("patientName", patientName);
		theModel.addAttribute("patientRecords", patientRecords);
		return "patientPreviousRecords";
	}

	@GetMapping(value = "/viewAllPatientRecords")
	public String viewAllPatientRecords(Model theModel) {
		List<Record> records = recordService.viewAllPatientRecords();
		theModel.addAttribute("records", records);
		return "list-patientRecords";

	}

	@GetMapping(value = "/viewRecordHistory")
	public String viewRecordHistory(Model theModel) {
		User user = userService.findByUsername(getPrincipal());
		Patient patient = patientService.findByUserId(user.getUserID());
		List<Record> patientRecords = recordService.getPreviousRecord(patient.getPatientID());
		String patientName = null;
		if (!patientRecords.isEmpty()) {
			patientName = patientRecords.get(0).getPatient().getLastName() + " "
					+ patientRecords.get(0).getPatient().getFirstName();
		}
		theModel.addAttribute("patientName", patientName);
		theModel.addAttribute("patientRecords", patientRecords);
		return "patientPreviousRecords";
	}

	@GetMapping(value = "/addComment")
	public String addComment(@RequestParam("recordId") int recordId, Model theModel) {
		Record record = recordService.getRecordById(recordId);
		Comment comment = new Comment();
		comment.setRecord(record);
		theModel.addAttribute("comment", comment);
		return "addComment";
	}

	@PostMapping(value = "/saveComment")
	public String saveComment(@ModelAttribute("comment") Comment comment) {
		recordService.saveComment(comment);
		return "redirect:/getWaitingList";
	}

	@GetMapping(value = "/showPatientRecord")
	public String showPatientRecord(@RequestParam("recordId") int recordId, Model tempModel) {
		Record record = recordService.getRecordById(recordId);
		tempModel.addAttribute("record", record);
		return "patientRecord";
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
