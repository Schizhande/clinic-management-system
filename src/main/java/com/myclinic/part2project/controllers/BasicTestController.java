package com.myclinic.part2project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.model.BasicTest;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.service.BasicTestService;
import com.myclinic.part2project.service.PatientService;
import com.myclinic.part2project.service.RecordService;

@Controller
public class BasicTestController {

	@Autowired
	PatientService patientService;

	@Autowired
	RecordService recordService;

	@Autowired
	BasicTestService basicTestService;

	@GetMapping(value = "/showBasicTestForm")
	public String showBasicTestForm(@RequestParam("recordId") int recordId, Model theModel) {
		BasicTest bt = new BasicTest();
		Record record = recordService.getRecordById(recordId);
		bt.setRecord(record);
		theModel.addAttribute("basicTest", bt);
		return "addBasicTest";
	}

	@PostMapping(value = "/saveBasicTest")
	public String saveBasicTest(@Valid @ModelAttribute("basicTest") BasicTest basicTest, BindingResult result) {
		if (result.hasErrors()) {
			return "addBasicTest";
		}
		 
		basicTestService.saveBasicTest(basicTest);
		return "redirect:/getWaitingList";

	}
}
