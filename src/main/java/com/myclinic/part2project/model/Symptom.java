package com.myclinic.part2project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "symptom")
public class Symptom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int symptomID;
	private String symptomName;
	
	@Transient
	private String src;
	@Transient
	private String dest;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="causedBy",
			joinColumns=@JoinColumn(name="symptomID"),
			inverseJoinColumns=@JoinColumn(name="diseaseID")
			)
	private List<Disease> disease;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="recordSymptom",
			joinColumns=@JoinColumn(name="symptomID"),
			inverseJoinColumns=@JoinColumn(name="recordID")
			)
	private List<Record> records;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
	@JoinTable(name = "symp_question", joinColumns = @JoinColumn(name = "symptomID"), inverseJoinColumns = @JoinColumn(name = "questionID"))
	private List<Question> questions;
	
	public int getSymptomID() {
		return symptomID;
	}

	public void setSymptomID(int symptomID) {
		this.symptomID = symptomID;
	}

	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Symptom [symptomID=" + symptomID + ", symptomName=" + symptomName + ", src=" + src + ", dest=" + dest
				+ "]";
	}

}
