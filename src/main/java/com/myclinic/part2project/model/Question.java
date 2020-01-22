package com.myclinic.part2project.model;

import java.util.List;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.myclinic.part2project.model.Symptom;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questionID")
	private int questionID;
	
	@Column(name="value", unique=true, nullable=false)
	private String value;

	@Transient
	private String weight;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
	@JoinTable(name = "symp_question", joinColumns = @JoinColumn(name = "questionID"), inverseJoinColumns = @JoinColumn(name = "symptomID"))
	private List<Symptom> symptoms;

	public Question() {

	}

	public Question(String value) {
		this.value = value;
	}

	public Question(String value, String weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	 	
}
