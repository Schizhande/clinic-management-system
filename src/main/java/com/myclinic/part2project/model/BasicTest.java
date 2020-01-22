package com.myclinic.part2project.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BASIC_TEST")
public class BasicTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int basicTestID;
	@NotNull(message = "is required")
	private Float weight;
	@NotNull(message = "is required")
	private Float temperature;
	@NotNull(message = "is required")
	private Float bp;

	@Transient
	private int patientID;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "recordID")
	private Record record;

	public int getBasicTestID() {
		return basicTestID;
	}

	public void setBasicTestID(int basicTestID) {
		this.basicTestID = basicTestID;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getBp() {
		return bp;
	}

	public void setBp(Float bp) {
		this.bp = bp;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	@Override
	public String toString() {
		return "BasicTest [basicTestID=" + basicTestID + ", weight=" + weight + ", temperature=" + temperature + ", bp="
				+ bp + ", patientID=" + patientID + ", record=" + record + "]";
	}

}
