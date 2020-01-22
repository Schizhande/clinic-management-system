package com.myclinic.part2project.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PAYMENT")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentID;

	@NotNull(message="is required")
	@Column(name = "AMOUNT")
	private Float amount;

	@ManyToOne
	@JoinColumn(name = "paymentMethodID")
	private PaymentMethod paymentMethod;

	@ManyToOne
	@JoinColumn(name = "paymentPurposeID")
	private PaymentPurpose paymentPurpose;

	@Column(name = "PAYMENT_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "recordID")
	private Record record;

	@ManyToOne
	@JoinTable(name = "sales_payment", joinColumns = @JoinColumn(name = "paymentID"), inverseJoinColumns = @JoinColumn(name = "salesID"))
	private Sales sales;

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentPurpose getPaymentPurpose() {
		return paymentPurpose;
	}

	public void setPaymentPurpose(PaymentPurpose paymentPurpose) {
		this.paymentPurpose = paymentPurpose;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

}
