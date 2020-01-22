package com.myclinic.part2project.controllers;

import java.util.ArrayList;
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

import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Payment;
import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.Sales;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.User;
import com.myclinic.part2project.service.PaymentService;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.SalesService;
import com.myclinic.part2project.service.StaffService;
import com.myclinic.part2project.service.StockService;
import com.myclinic.part2project.service.UserService;

@Controller
public class PaymentController {

	@Autowired
	RecordService recordService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	UserService userService;

	@Autowired
	StaffService staffService;

	@Autowired
	StockService stockService;

	@Autowired
	SalesService salesService;
	
	@GetMapping(value="/listPayments")
	public String listPayments(Model theModel){
		List<Payment> payments= paymentService.getPayments();
		theModel.addAttribute("payments", payments);
		return "list-payment";
		
	}

	@GetMapping(value = "/showPaymentForm")
	public String showPaymentForm(@RequestParam("recordId") int recordId, Model theModel) {
		Record record = recordService.getRecordById(recordId);
		Payment payment = new Payment();
		payment.setRecord(record);
		theModel.addAttribute("methods", paymentService.getPaymentMethods());
		theModel.addAttribute("purposes", paymentService.getPaymentPurposes());
		theModel.addAttribute("payment", payment);
		return "makePayment";
	}

	@GetMapping(value = "/purchaseMedicine")
	public String purchaseMedicine(@RequestParam("recordId") int recordId, @RequestParam("totalAmount") float amount,
			Model theModel) {
		if(amount==0.0){
			List<Drug> drugNotInStock= new ArrayList<>();
			List<Drug> drugInStock= new ArrayList<>();
			float totalPrice=0.0f;
			Record record= recordService.getRecordById(recordId);
			for(Prescription presc: record.getPrescription()){
				int noOfIterms= stockService.getStockByDrugID(presc.getDrug().getDrugID());
				if(noOfIterms>presc.getDrug().getQuantity()){
					totalPrice+=presc.getDrug().getPrice();
					drugInStock.add(presc.getDrug());
				}else{
					drugNotInStock.add(presc.getDrug());
				}
			}
			theModel.addAttribute("recordId", recordId);
			theModel.addAttribute("totalPrice", totalPrice);
			theModel.addAttribute("drugInStock", drugInStock );
			theModel.addAttribute("drugNotInStock",drugNotInStock);
			theModel.addAttribute("noDrug",true);
			return "prepareForPayment";
		}
		Record record = recordService.getRecordById(recordId);
		Payment payment = new Payment();
		payment.setRecord(record);
		payment.setAmount(amount);
		theModel.addAttribute("methods", paymentService.getPaymentMethods());
		theModel.addAttribute("purposes", paymentService.getPaymentPurposes());
		theModel.addAttribute("payMedicine", true);
		theModel.addAttribute("payment", payment);
		return "makePayment";
	}

	@PostMapping(value = "/savePayment")
	public String savePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult result, Model theModel) {
		if (result.hasErrors()) {
			theModel.addAttribute("methods", paymentService.getPaymentMethods());
			theModel.addAttribute("purposes", paymentService.getPaymentPurposes());
			return "makePayment";
		}
		paymentService.savePayment(payment);
		List<Record> waitings = recordService.getWaitingList();
		theModel.addAttribute("patients", waitings);
		theModel.addAttribute("paySuccess", true);
		return "waitingList";
	}

	@PostMapping(value = "/payMedicineBill")
	public String payMedicineBill(@Valid @ModelAttribute("payment") Payment payment, BindingResult result,
			Model theModel) {

		if (result.hasErrors()) {
			theModel.addAttribute("methods", paymentService.getPaymentMethods());
			theModel.addAttribute("purposes", paymentService.getPaymentPurposes());
			return "makePayment";
		}

		List<Drug> drugNotInStock = new ArrayList<>();
		List<Drug> drugInStock = new ArrayList<>();
		Record record = recordService.getRecordById(payment.getRecord().getRecordID());
		for (Prescription presc : record.getPrescription()) {
			int noOfIterms = stockService.getStockByDrugID(presc.getDrug().getDrugID());
			if (noOfIterms > presc.getDrug().getQuantity()) {
				drugInStock.add(presc.getDrug());
			} else {
				drugNotInStock.add(presc.getDrug());
			}
		}
		User u = userService.findByUsername(getPrincipal());
		Staff staff = staffService.getStaffByUserId(u.getUserID());
		Sales sale = new Sales();
		sale.setDrugs(drugInStock);
		sale.setStaff(staff);
		sale.setPayment(payment);
		paymentService.savePayment(payment);
		payment.setSales(sale);
		salesService.saveSales(sale);
	
		
		List<Record> waitings = recordService.getWaitingList();
		theModel.addAttribute("patients", waitings);
		theModel.addAttribute("paySuccess", true);
		return "waitingList";
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
