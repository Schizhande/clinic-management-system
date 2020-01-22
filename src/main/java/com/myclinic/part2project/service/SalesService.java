package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Sales;

public interface SalesService {

	void saveSales(Sales sale);

	List<Sales> getSales();

}
