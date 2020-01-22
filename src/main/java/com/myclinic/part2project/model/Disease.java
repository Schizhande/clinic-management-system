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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "disease")
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int diseaseID;
	private String diseaseName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="causedBy",
			joinColumns=@JoinColumn(name="diseaseID"),
			inverseJoinColumns=@JoinColumn(name="symptomID")
			)
	private List<Symptom> symptoms;


	@OneToMany(mappedBy="disease", cascade=CascadeType.ALL)
	private List<RecordDisease> recordSymptom;


	public int getDiseaseID() {
		return diseaseID;
	}


	public void setDiseaseID(int diseaseID) {
		this.diseaseID = diseaseID;
	}


	public String getDiseaseName() {
		return diseaseName;
	}


	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}


	public List<Symptom> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}


	public List<RecordDisease> getRecordSymptom() {
		return recordSymptom;
	}


	public void setRecordSymptom(List<RecordDisease> recordSymptom) {
		this.recordSymptom = recordSymptom;
	}


	@Override
	public String toString() {
		return "Disease [diseaseID=" + diseaseID + ", diseaseName=" + diseaseName + "]";
	}
	
	
 
}
