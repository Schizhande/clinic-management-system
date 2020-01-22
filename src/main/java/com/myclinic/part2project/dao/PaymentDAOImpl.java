package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Payment;
import com.myclinic.part2project.model.PaymentMethod;
import com.myclinic.part2project.model.PaymentPurpose;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void savePayment(Payment payment) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(payment);
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<PaymentMethod> theQuery = currentSession.createQuery("from PaymentMethod", PaymentMethod.class);
		List<PaymentMethod> paymentMethod = theQuery.getResultList();
		return paymentMethod;
	}

	@Override
	public List<PaymentPurpose> getPaymentPurposes() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<PaymentPurpose> theQuery = currentSession.createQuery("from PaymentPurpose ", PaymentPurpose.class);
		List<PaymentPurpose> paymentPurposes = theQuery.getResultList();
		return paymentPurposes;
	}

	@Override
	public List<Payment> getPayments() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Payment> theQuery = currentSession.createQuery("from Payment ", Payment.class);
		List<Payment> payments = theQuery.list();
		return payments;
	}

}
