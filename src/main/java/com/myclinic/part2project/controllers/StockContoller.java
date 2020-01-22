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

import com.myclinic.part2project.model.Stock;
import com.myclinic.part2project.service.DrugService;
import com.myclinic.part2project.service.StockService;

@Controller
public class StockContoller {

	@Autowired
	DrugService drugService;
	
	@Autowired
	StockService stockService;
	
	@GetMapping(value="/addStock")
	public String addStock(Model theModel){
		Stock stock =new Stock();
		theModel.addAttribute("drugs", drugService.getDrugs());
		theModel.addAttribute("stock", stock);
		return "addStock";
	}
	
	@PostMapping(value="/saveStock")
	public String saveStock(@Valid @ModelAttribute("stock") Stock stock,BindingResult result,Model theModel){
		if(result.hasErrors()){
			theModel.addAttribute("drugs", drugService.getDrugs());
			return "addStock";
		}
		stockService.saveStock(stock);
		return "redirect:/getStock";
	}
	
	@GetMapping(value="/getStock")
	public String getStock(Model theModel){
		theModel.addAttribute("stocks", stockService.getStock());
		return "list-stocks";
	}
	@GetMapping(value="/showFormForStockUpdate")
	public String showFormForStockUpdate(@RequestParam("stockId")int stockId, Model theModel){
		Stock stock= stockService.getStock(stockId);
		theModel.addAttribute("drugs", drugService.getDrugs());
		theModel.addAttribute("stock", stock );
		return "addStock";
	}

	@GetMapping(value="/deleteStock")
	public String deleteStock(@RequestParam("stockId")int stockId,Model theModel){
		stockService.deleteStock(stockId);
		return "redirect:/getStock";
	}
}
