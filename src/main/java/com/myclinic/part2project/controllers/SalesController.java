package com.myclinic.part2project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myclinic.part2project.model.Sales;
import com.myclinic.part2project.service.SalesService;

@Controller
public class SalesController {

	
	@Autowired
	SalesService salesService;
	
	@GetMapping(value="/listSales")
	public String listSales(Model theModel){
		List<Sales> sales= salesService.getSales();
		theModel.addAttribute("sales",sales);
		return "list-sales";
	}
}
