package com.myclinic.part2project.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffID;

	@NotEmpty(message = "is required")
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Size(min = 1, message = "is required")
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotEmpty(message = "is required")
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "dob")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Size(min = 1, message = "is required")
	@Column
	private String mobile;

	@Size(min = 1, message = "is required")
	@Column
	private String nationalID;

	@Size(min = 1, message = "is required")
	@Column
	private String address;

	@Column(name = "JOINED_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joinedDate = LocalDate.now();

	@Valid
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maritalID")
	private MaritalStatus maritalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genderID")
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobTitleID")
	private JobTitle jobTitle;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sales_pharmacist", joinColumns = @JoinColumn(name = "pharmacistID"), inverseJoinColumns = @JoinColumn(name = "salesID"))
	private List<Sales> sales;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Schedule> schedules;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	private List<Appointment> appointment;

	@OneToMany(mappedBy = "record", fetch = FetchType.LAZY)
	private List<ExaminedBy> examinedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "doctor_specialty", joinColumns = @JoinColumn(name = "doctorID"), inverseJoinColumns = @JoinColumn(name = "specialtyID"))
	private Specialty specialty;

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Sales> getSales() {
		return sales;
	}

	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(LocalDate joinedDate) {
		this.joinedDate = joinedDate;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<ExaminedBy> getExaminedBy() {
		return examinedBy;
	}

	public void setExaminedBy(List<ExaminedBy> examinedBy) {
		this.examinedBy = examinedBy;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

}
