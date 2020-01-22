package com.myclinic.part2project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examinedBy")
public class ExaminedBy implements Serializable{

 
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "doctorOrNurseID")
	private Staff doctorOrNurse;

	@Id
	@ManyToOne
	@JoinColumn(name = "recordID")
	private Record record;

	public Staff getDoctorOrNurse() {
		return doctorOrNurse;
	}

	public void setDoctorOrNurse(Staff doctorOrNurse) {
		this.doctorOrNurse = doctorOrNurse;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
