package com.myclinic.part2project.service;

import com.myclinic.part2project.model.Stock;

public interface StockService {

	void saveStock(Stock stock);

	Object getStock();

	Stock getStock(int stockId);

	void deleteStock(int stockId);

	int getStockByDrugID(int drugID);

}
