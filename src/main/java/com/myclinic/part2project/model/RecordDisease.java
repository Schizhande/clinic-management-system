package com.myclinic.part2project.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recordDisease")
public class RecordDisease {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne 
	@JoinColumn(name="recordID")
	private Record record;
	
	@ManyToOne 
	@JoinColumn(name="diseaseID")
	private Disease disease;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Override
	public String toString() {
		return "RecordDisease [id=" + id + ", disease=" + disease + "]";
	}
	
	
}
