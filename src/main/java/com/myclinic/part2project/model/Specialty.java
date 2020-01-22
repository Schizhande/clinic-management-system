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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="specilty")
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int specialtyID;
	private String name;
	
	@OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(
			name="doctor_specialty",
			joinColumns=@JoinColumn(name="specialtyID"),
			inverseJoinColumns=@JoinColumn(name="doctorID")
			)
	private List<Staff> doctor;

	public int getSpecialtyID() {
		return specialtyID;
	}

	public void setSpecialtyID(int specialtyID) {
		this.specialtyID = specialtyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Staff> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Staff> doctor) {
		this.doctor = doctor;
	}

}
