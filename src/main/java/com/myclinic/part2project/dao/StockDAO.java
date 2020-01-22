package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Stock;

public interface StockDAO {

	void saveStock(Stock stock);

	Object getStock();

	Stock getStock(int stockId);

	 void deleteStock(int stockId);

	List<Stock> getStockByDrugID(int drugID);

}
