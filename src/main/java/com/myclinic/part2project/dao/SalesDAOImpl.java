package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Sales;
import com.myclinic.part2project.model.Stock;

@Repository
public class SalesDAOImpl implements SalesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveSales(Sales sale) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(sale);
	}

	@Override
	public List<Sales> getSales() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Sales> theQuery = currentSession.createQuery("from Sales ", Sales.class);
		List<Sales> sales = theQuery.getResultList();
		for(Sales sale: sales){
			Hibernate.initialize(sale.getDrugs());
			Hibernate.initialize(sale.getStaff());
			Hibernate.initialize(sale.getPayment());
		}

		return sales;
	}

}
