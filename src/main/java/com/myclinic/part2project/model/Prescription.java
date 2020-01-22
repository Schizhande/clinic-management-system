package com.myclinic.part2project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="prescriptions")
public class Prescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne 
	@JoinColumn(name="drugID")
	private Drug drug;
	
	@Id
	@ManyToOne 
	@JoinColumn(name="recordID")
	private Record record;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
	
	

}
