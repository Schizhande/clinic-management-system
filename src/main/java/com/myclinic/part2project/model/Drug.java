package com.myclinic.part2project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int drugID;

	@NotEmpty(message="is required")
	@Column(name = "NAME")
	private String name;

	@NotEmpty(message="is required")
	@Column(name = "DOSAGE")
	private String dosage;

	@NotNull
	@Column(name = "PRICE")
	private Float price;
	
	@Transient
	private Integer recordId;

	@NotEmpty(message="is required")
	@Column(name = "FREQUENCY")
	private String frequency;
	
	@NotNull
	@Column(name = "drugQuantity")
	private Integer quantity;

	@NotNull
	@Column(name = "DURATION")
	private Integer duration;
 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "supplies", joinColumns = @JoinColumn(name = "drugID"), inverseJoinColumns = @JoinColumn(name = "supplierID"))
	private List<Supplier> suppliers;

	@OneToMany(mappedBy = "drug")
	private List<Stock> stocks;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "drug_sales", joinColumns = @JoinColumn(name = "drugID"), inverseJoinColumns = @JoinColumn(name = "salesID"))
	private List<Sales> sales;
	
	
	@OneToMany(mappedBy="drug", cascade=CascadeType.ALL)
	private List<Prescription> prescription;
	

	public void setDrugID(int drugID) {
		this.drugID = drugID;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDrugID() {
		return drugID;
	}

	public void setDrugID(Integer drugID) {
		this.drugID = drugID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public List<Sales> getSales() {
		return sales;
	}

	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return "Drug [drugID=" + drugID + ", name=" + name + ", dosage=" + dosage + ", price=" + price + ", frequency="
				+ frequency + "]";
	}

}
