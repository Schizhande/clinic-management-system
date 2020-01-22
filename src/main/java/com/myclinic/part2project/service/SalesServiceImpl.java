package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.SalesDAO;
import com.myclinic.part2project.model.Sales;
import com.myclinic.part2project.model.Stock;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	SalesDAO salesDao;

	@Override
	public void saveSales(Sales sale) {
		 salesDao.saveSales(sale);

	}

	@Override
	public List<Sales> getSales() {
		return salesDao.getSales();
	}

}
