package com.myclinic.part2project.dao;

import java.time.LocalDate;
import java.util.List;

import com.myclinic.part2project.model.Appointment;

public interface AppointmentDao {

	void saveAppointment(Appointment appointment);

	List<Appointment> getDoctorAppointmentPerDay(LocalDate tempDate, int doctorId);

	List<Appointment> getAppointments();

	Appointment getAppointment(int appointID);

	Appointment getAppointmentByPatientId(int patientID, int scheduleId);

	void deleteAppointment(int appointmentId);

	List<Appointment> appointmentListPerDoctor(int staffID);

}
