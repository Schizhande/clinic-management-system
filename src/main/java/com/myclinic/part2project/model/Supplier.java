package com.myclinic.part2project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name= "SUPPLIER")
public class Supplier {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierID;
	private String name;
	private String address;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="supplies",
			joinColumns=@JoinColumn(name="supplierID"),
			inverseJoinColumns=@JoinColumn(name="stockID")
			)
	private List<Stock> stocks;
	
	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}
