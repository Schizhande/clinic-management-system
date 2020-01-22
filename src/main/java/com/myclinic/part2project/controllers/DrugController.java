package com.myclinic.part2project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.service.DrugService;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.StockService;

import net.sf.jasperreports.data.http.RequestMethod;

@Controller
public class DrugController {

	@Autowired
	DrugService drugService;

	@Autowired
	RecordService recordService;

	@Autowired
	StockService stockService;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@GetMapping(value = "/showDrugForm")
	public String showDrugForm(Model theModel) {
		Drug drug = new Drug();
		theModel.addAttribute("drug", drug);
		return "addDrug";
	}

	@PostMapping(value = "/saveDrug")
	public String saveDrug(@Valid @ModelAttribute("drug") Drug drug, BindingResult result) {
		if (result.hasErrors())
			return "addDrug";
		drugService.saveDrug(drug);
		return "redirect:/getDrugs";
	}

	@GetMapping(value = "/getDrugs")
	public String getDrugs(Model theModel) {
		theModel.addAttribute("drugs", drugService.getDrugs());
		return "list-drugs";
	}

	@GetMapping(value = "/updateDrug")
	public String getDrug(@RequestParam("drugId") int drugId, Model theModel) {
		Drug drug = drugService.getDrug(drugId);
		theModel.addAttribute("drug", drug);
		return "addDrug";
	}

	@GetMapping(value = "/issueDrugs")
	private String issueDrugs(@RequestParam("recordId") int recordId, Model theModel) {
		List<Drug> drugNotInStock = new ArrayList<>();
		List<Drug> drugInStock = new ArrayList<>();
		float totalPrice = 0.0f;
		Record record = recordService.getRecordById(recordId);
		for (Prescription presc : record.getPrescription()) {
			int noOfIterms = stockService.getStockByDrugID(presc.getDrug().getDrugID());
			if (noOfIterms > presc.getDrug().getQuantity()) {
				totalPrice += presc.getDrug().getPrice();
				drugInStock.add(presc.getDrug());
			} else {
				drugNotInStock.add(presc.getDrug());
			}
		}
		theModel.addAttribute("recordId", recordId);
		theModel.addAttribute("totalPrice", totalPrice);
		theModel.addAttribute("drugInStock", drugInStock);
		theModel.addAttribute("drugNotInStock", drugNotInStock);
		return "prepareForPayment";
	}

	@GetMapping(value = "/getTags")
	public @ResponseBody List<Drug> getTags(@RequestParam String tagName) {

		return simulateSearchResult(tagName);

	}

	private List<Drug> simulateSearchResult(String tagName) {

		List<Drug> result = new ArrayList<Drug>();
		for (Drug drug : drugService.getDrugs()) {
			if (drug.getName().contains(tagName)) {
				result.add(drug);
			}

		}
		return result;
	}

}
