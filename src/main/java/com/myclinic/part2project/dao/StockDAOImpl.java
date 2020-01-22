package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Stock;

@Repository
public class StockDAOImpl implements StockDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveStock(Stock stock) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(stock);
	}

	@Override
	public Object getStock() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Stock> theQuery = currentSession.createQuery("from Stock ", Stock.class);
		List<Stock> stocks = theQuery.getResultList();
		return stocks;
	}

	@Override
	public Stock getStock(int stockId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Stock stock =currentSession.get(Stock.class, stockId);
		return stock;
	}

	@Override
	public void deleteStock(int stockId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Stock stock =currentSession.get(Stock.class, stockId);
		currentSession.delete(stock);
	}

	@Override
	public List<Stock> getStockByDrugID(int drugID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria= currentSession.createCriteria(Stock.class);
		criteria.add(Restrictions.eq("drug.drugID", drugID));
		List<Stock> stocks= criteria.list();
		return stocks ;
	}

}
