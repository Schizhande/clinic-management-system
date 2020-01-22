package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.PaymentDAO;
import com.myclinic.part2project.model.Payment;
import com.myclinic.part2project.model.PaymentMethod;
import com.myclinic.part2project.model.PaymentPurpose;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDAO paymentDAO;
	
	@Override
	public void savePayment(Payment payment) {
		 paymentDAO.savePayment(payment);

	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		return paymentDAO.getPaymentMethods();
	}

	@Override
	public List<PaymentPurpose> getPaymentPurposes() {
		return paymentDAO.getPaymentPurposes();
	}

	@Override
	public List<Payment> getPayments() {
		return paymentDAO.getPayments();
	}

}
