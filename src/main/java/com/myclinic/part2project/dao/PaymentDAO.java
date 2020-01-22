package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Payment;
import com.myclinic.part2project.model.PaymentMethod;
import com.myclinic.part2project.model.PaymentPurpose;

public interface PaymentDAO {

	void savePayment(Payment payment);

	List<PaymentMethod> getPaymentMethods();

	List<PaymentPurpose> getPaymentPurposes();

	List<Payment> getPayments();

}
