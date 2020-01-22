package com.myclinic.part2project.controllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclinic.part2project.bean.Graph;
import com.myclinic.part2project.bean.SymptomService;
import com.myclinic.part2project.bean.TreatmentBean;
import com.myclinic.part2project.model.Disease;
import com.myclinic.part2project.model.ExaminedBy;
import com.myclinic.part2project.model.Question;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.RecordDisease;
import com.myclinic.part2project.model.RecordSymptom;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.Symptom;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.DiseaseService;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.RecordSymptomService;
import com.myclinic.part2project.service.StaffService;
import com.myclinic.part2project.service.UserService;

@Controller
public class TreatmentController {

	@Autowired
	SymptomService symptomService;

	@Autowired
	RecordService recordService;

	@Autowired
	StaffService staffService;

	@Autowired
	UserService userService;

	@Autowired
	RecordSymptomService recordSymptomService;

	@Autowired
	DiseaseService diseaseService;

	public Graph graph;
	public Queue<String> queue;
	public Set<String> visited;
	public List<TreatmentBean> charts;
	private Logger myLogger = Logger.getLogger(getClass().getName());

	@PostConstruct
	public void init() {
		myLogger.info(">>>>>>>>>>initializing graph");
		graph = new Graph();
		List<Question> questions = symptomService.getQuestions();
		graph.addQuestion(questions);
		List<Symptom> ques = new ArrayList<>();
		List<Symptom> sy = symptomService.getSymptoms();
		for (Symptom sym : sy) {
			Symptom s1 = new Symptom();
			s1.setSymptomName(sym.getSymptomName());

			for (Question nnn : sym.getQuestions()) {
				if (s1.getSrc() == null) {
					s1.setSrc(nnn.getValue());
				}
				s1.setDest(nnn.getValue());
			}
			ques.add(s1);
			s1 = null;
		}
		graph.addEdge(ques);
		queue = new LinkedList<String>();
		visited = new LinkedHashSet<String>();
		charts= new ArrayList<>();

	}

	@GetMapping(value = "/showTreatmentForm")
	public String createGraph(@RequestParam("recordId") int recordID, Model theModel) {
		myLogger.info(">>>>>>>>>>>>>>>create graph");
		visited.clear();
		queue.clear();
		charts.clear();
		queue.add("What is the patient's condition");
		visited.add("What is the patient's condition");

		return "redirect:/treatPatient?recordId=" + recordID;
	}

	@GetMapping(value = "/treatPatient")
	public String processTrea(@RequestParam("recordId") int recordID, HttpServletRequest request, Model theModel) {
		String answer = request.getParameter("answer");
		if (answer == null) {
			String vertex = queue.element();
			myLogger.info(">>>>> vertex" + vertex);
			//theModel.addAttribute("findD", false);
			theModel.addAttribute("recordId", recordID);
			charts.add(new TreatmentBean(vertex));
			theModel.addAttribute("charts", charts);
			theModel.addAttribute("q", vertex);
			theModel.addAttribute("graph", graph);
			theModel.addAttribute("visited", visited);
				myLogger.info(">>>>>" + queue);
			return "treatment";
		} else {
			graph.setAnswer(answer);
			RecordSymptom symptoms = new RecordSymptom();
			RecordDisease rd = new RecordDisease();
			Record record = recordService.getRecordById(recordID);
			String vertex = null;
			if (!queue.isEmpty()) {

				String vert = null;
				vertex = queue.poll();
				for(TreatmentBean treat: charts){
					if(treat.getQuestion().equalsIgnoreCase(vertex)){
						treat.setAnswer(answer);
					}
				}
				for (Question quest : graph.getAdj(vertex)) {
					if (!visited.contains(quest.getValue())) {
						if (quest.getWeight().equals(graph.getAnswer())) {
							visited.add(quest.getValue());
							queue.add(quest.getValue());
							Symptom symptom = symptomService.getSymptomByName(answer);
							if (!recordService.isSymptomMapped(symptom.getSymptomID(), record.getRecordID())) {
								symptoms.setRecord(record);
								symptoms.setSymptom(symptom);
								recordSymptomService.saveSymptom(symptoms);
							}
							vert = queue.element();
							List<Disease> diseases = diseaseService.getDiseases();
							for (Disease d : diseases) {
								if (d.getDiseaseName().equalsIgnoreCase(vert)) {
									if (!recordService.isDiseaseMapped(d.getDiseaseID(), record.getRecordID())) {
										rd.setDisease(d);
										rd.setRecord(record);
										diseaseService.saveRecordDisease(rd);

									}
									theModel.addAttribute("q", vert);
									theModel.addAttribute("visited", visited);
									theModel.addAttribute("recordId", recordID);
									theModel.addAttribute("findD", true);
									TreatmentBean tr= new TreatmentBean(vert);
									tr.setFound(true);
									charts.add(tr);
									theModel.addAttribute("charts", charts);
									queue.poll();
									ExaminedBy examinedBy = new ExaminedBy();
									User u = userService.findByUsername(getPrincipal());
									Staff staff = staffService.getStaffByUserId(u.getUserID());
									if (!recordService.isMapped(record.getRecordID(), staff.getStaffID())) {
										examinedBy.setDoctorOrNurse(staff);
										examinedBy.setRecord(record);
										recordService.saveExaminedBy(examinedBy);

									}
									return "treatment";

								}

							}

						}

					}
				}
				if (!queue.isEmpty()) {
					vert = queue.element();
					theModel.addAttribute("q", vert);
					TreatmentBean tr= new TreatmentBean(vert);
					tr.setFound(true);
					charts.add(tr);
				 
					theModel.addAttribute("charts", charts);
					theModel.addAttribute("recordId", recordID);
					theModel.addAttribute("visited", visited);
					for(String a : visited){
						myLogger.info(">>>>>" + a);
					}
					return "treatment";
				} else {
					return "redirect:/showPatientRecord?recordId=" + recordID;
				}

			} else {
				return "redirect:/showPatientRecord?recordId=" + recordID;
			}
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
