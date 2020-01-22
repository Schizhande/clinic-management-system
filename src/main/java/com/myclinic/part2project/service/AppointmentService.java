package com.myclinic.part2project.service;

import java.time.LocalDate;
import java.util.List;

import com.myclinic.part2project.model.Appointment;

public interface AppointmentService {

	void saveAppointment(Appointment appointment);

	List<Appointment> getDoctorAppointmentPerDay(LocalDate tempDate, int doctorId);

	List<Appointment> getAppointments();

	void acceptAppointment(int appointID);

	void declineAppointment(int appointID);

	Appointment getAppointment(int patientID, int scheduleId);

	void editAppointment(Appointment appoint);

	Appointment getAppointmen(int appointId);

	void deleteAppointment(int appointmentId);

	List<Appointment> appointmentListPerDoctor(int staffID);

}
