package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Sales;
import com.myclinic.part2project.model.Stock;

public interface SalesDAO {

	void saveSales(Sales sale);

	List<Sales> getSales();

}
