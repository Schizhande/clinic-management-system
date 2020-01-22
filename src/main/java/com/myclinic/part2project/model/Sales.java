package com.myclinic.part2project.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salesID;
	private Integer quantity=8;
	private LocalDate issedDate=LocalDate.now();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "drug_sales", joinColumns = @JoinColumn(name = "salesID"), inverseJoinColumns = @JoinColumn(name = "drugID"))
	private List<Drug> drugs;

	@OneToOne
	@JoinTable(name = "sales_payment", joinColumns = @JoinColumn(name = "salesID"), inverseJoinColumns = @JoinColumn(name = "paymentID"))
	private Payment payment;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinTable(
			name="sales_pharmacist",
			joinColumns=@JoinColumn(name="salesID"),
			inverseJoinColumns=@JoinColumn(name="pharmacistID")
			)
	private Staff staff;

	public int getSalesID() {
		return salesID;
	}

	public void setSalesID(int salesID) {
		this.salesID = salesID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getIssedDate() {
		return issedDate;
	}

	public void setIssedDate(LocalDate issedDate) {
		this.issedDate = issedDate;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
