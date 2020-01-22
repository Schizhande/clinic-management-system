package com.myclinic.part2project.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.StockDAO;
import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Stock;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	StockDAO stockDAO;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Override
	public void saveStock(Stock stock) {
		stockDAO.saveStock(stock);
	}

	@Override
	public Object getStock() {
		return stockDAO.getStock();
	}

	@Override
	public Stock getStock(int stockId) {
		return stockDAO.getStock(stockId);
	}

	@Override
	public void deleteStock(int stockId) {
		stockDAO.deleteStock(stockId);
	}

	@Override
	public int getStockByDrugID(int drugID) {
		int noOfIterm = 0;
		for (Stock stock : stockDAO.getStockByDrugID(drugID)) {
			noOfIterm += stock.getQuantity();
		}
		myLogger.info(">>>>>>>>>number of iterms" + noOfIterm);
		return noOfIterm;
	}

}
