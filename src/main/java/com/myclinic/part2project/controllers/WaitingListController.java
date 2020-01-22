package com.myclinic.part2project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.RecordService;

@Controller
public class WaitingListController {
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	PatientService patientService;
	
	@GetMapping(value="/getWaitingList")
	public String getWaitingList(Model theModel){
		List<Record> waitings= recordService.getWaitingList();
		theModel.addAttribute("patients", waitings);
		return "waitingList";
	}
	
	@GetMapping(value="/addToWaitingList")
	public String addToWaitingList(@RequestParam("patientId") int patientId,	Model theModel){
		if(recordService.hasPandingVisit(patientId)){
			theModel.addAttribute("failAdd",true);
			return "patientSearch";
		}
		Patient patient = patientService.getPatient(patientId);
		Record record = new Record();
		record.setPatient(patient);	
		record.setRecordStatus("Pending");
		recordService.saveRecord(record);
		List<Record> waitings= recordService.getWaitingList();
		theModel.addAttribute("patients", waitings);
		theModel.addAttribute("addSuccess",true);
		return "waitingList";
	}
	@GetMapping(value = "/closeRecord")
	private String closeRecord(@RequestParam("recordId") int recordId) {
		recordService.closeRecord(recordId);
		return "redirect:/getWaitingList";
	}

	@GetMapping(value = "/reAddToWaiting")
	private String reAddToWaiting(@RequestParam("recordId") int recordId) {
		recordService.reAddToWaiting(recordId);
		return "redirect:/getWaitingList";
	}

}
