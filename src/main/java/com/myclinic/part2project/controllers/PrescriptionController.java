package com.myclinic.part2project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.service.DrugService;
import com.myclinic.part2project.service.PrescriptionService;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.StockService;

@Controller
public class PrescriptionController {

	@Autowired
	RecordService recordService;

	@Autowired
	DrugService drugService;

	@Autowired
	StockService stockService;

	@Autowired
	PrescriptionService prescriptionService;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@GetMapping(value = "/prescribe")
	public String prescribePatient(@RequestParam("recordId") int recordID, Model theModel) {
		Record record = recordService.getRecordById(recordID);
		theModel.addAttribute("drugs", record.getPrescription());
		theModel.addAttribute("record", record);
		return "write-prescription";
	}

	@GetMapping(value = "/prescribePatient")
	public String addDrug(HttpServletRequest request, Model theModel) {
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		String drugName = request.getParameter("drugName");
		Drug drug = drugService.getDrugByName(drugName);
		myLogger.info(">>>>>>>>>>>drug in prescription " + drug);
		if (drug == null) {
			Drug dru = new Drug();
			dru.setName(drugName);
			dru.setRecordId(recordId);
			theModel.addAttribute("drug", dru);
			theModel.addAttribute("prescription", true);
			theModel.addAttribute("recordId", recordId);
			return "addDrug";
		}

		Prescription pre = new Prescription();
		Record record = recordService.getRecordById(recordId);
		Prescription pres = prescriptionService.getPrescribeDrug(drug.getDrugID(), record.getRecordID());
		myLogger.info(">>>>>>>>already prescribed drug" + pres);
		if (pres != null) {
			theModel.addAttribute("drugs", record.getPrescription());
			theModel.addAttribute("record", record);
			theModel.addAttribute("alreadyAdded", true);
			return "write-prescription";
		}
		pre.setDrug(drug);
		pre.setRecord(record);
		prescriptionService.savePrecription(pre);
		theModel.addAttribute("record", record);
		theModel.addAttribute("drugs", record.getPrescription());
		return "write-prescription";
	}

	@PostMapping(value = "/saveDrugPlusPrescript")
	public String saveDrugPlusPrescript(@ModelAttribute("drug") Drug drug, Model theModel) {
		Prescription pre = new Prescription();
		Record record = recordService.getRecordById(drug.getRecordId());
		System.out.println(">>>>>>>>>>>:" + record);
		drugService.saveDrug(drug);
		pre.setDrug(drug);
		pre.setRecord(record);
		List<Prescription> p = new ArrayList();
		p.add(pre);
		record.setPrescription(p);
		prescriptionService.savePrecription(pre);
		theModel.addAttribute("record", record);
		theModel.addAttribute("drugs", record.getPrescription());
		return "write-prescription";
	}

	@GetMapping(value = "/removeDrug")
	public String removeDrug(@RequestParam("drugId") int drugId, @RequestParam("recordId") int recordId,
			Model theModel) {
		prescriptionService.removeDrug(drugId, recordId);
		Record record = recordService.getRecordById(recordId);
		theModel.addAttribute("record", record);
		theModel.addAttribute("drugs", record.getPrescription());
		return "write-prescription";
	}

	@GetMapping(value = "/printPrecriptionPdf")
	public ModelAndView printPrecriptionPdf(@RequestParam("recordId") int recordId) {
		List<Drug> drugNotInStock = new ArrayList<>();
		List<Drug> drugInStock = new ArrayList<>();
		Record record = recordService.getRecordById(recordId);
		for (Prescription presc : record.getPrescription()) {
			int noOfIterms = stockService.getStockByDrugID(presc.getDrug().getDrugID());
			if (noOfIterms > presc.getDrug().getQuantity()) {
				drugInStock.add(presc.getDrug());
			} else {
				drugNotInStock.add(presc.getDrug());
			}
		}
		return new ModelAndView("prescriptionPdfView", "record", record);
	}

	@GetMapping(value = "/viewPrescription")
	public String viewPrescription(@RequestParam("recordId") int recordId, Model theModel) {
		List<Prescription> presc = prescriptionService.viewPrescription(recordId);
		theModel.addAttribute("pres", presc);
		return "prescription";
	}
}
