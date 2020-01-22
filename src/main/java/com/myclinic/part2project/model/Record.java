package com.myclinic.part2project.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RECORD")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordID;
	private LocalDate visitDate=LocalDate.now();
	private String recordStatus;

	@OneToOne(mappedBy = "record", fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	private BasicTest basictest;

	@OneToMany(mappedBy = "record",fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	private List<Payment> payments;

	@OneToMany(mappedBy = "record",fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	private List<ExaminedBy> examinedBy;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="patientID")
	private Patient patient;
	
	@OneToOne(mappedBy="record",cascade=CascadeType.ALL)
	private Comment comment;
	
	@OneToMany(mappedBy="record", cascade=CascadeType.ALL)
	private List<RecordSymptom> recordSymptom;
	
	@OneToMany(mappedBy="record", cascade=CascadeType.ALL)
	private List<RecordDisease> recordDisease;
	
	
	@OneToMany(mappedBy="record", cascade=CascadeType.ALL)
	private List<Prescription> prescription;

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public BasicTest getBasictest() {
		return basictest;
	}

	public void setBasictest(BasicTest basictest) {
		this.basictest = basictest;
	}
 

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Comment getComment() {
		return comment;
	}

	public List<RecordDisease> getRecordDisease() {
		return recordDisease;
	}

	public void setRecordDisease(List<RecordDisease> recordDisease) {
		this.recordDisease = recordDisease;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<RecordSymptom> getRecordSymptom() {
		return recordSymptom;
	}

	public void setRecordSymptom(List<RecordSymptom> recordSymptom) {
		this.recordSymptom = recordSymptom;
	}

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public List<ExaminedBy> getExaminedBy() {
		return examinedBy;
	}

	public void setExaminedBy(List<ExaminedBy> examinedBy) {
		this.examinedBy = examinedBy;
	}

	@Override
	public String toString() {
		return "Record [recordID=" + recordID + ", visitDate=" + visitDate + ", recordStatus=" + recordStatus + "]";
	}

 
}
