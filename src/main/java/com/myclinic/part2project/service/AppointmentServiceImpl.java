package com.myclinic.part2project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.AppointmentDao;
import com.myclinic.part2project.model.AppointStatus;
import com.myclinic.part2project.model.Appointment;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDao appointmentDAO;

	@Override
	public void saveAppointment(Appointment appointment) {
		appointmentDAO.saveAppointment(appointment);
	}

	@Override
	public List<Appointment> getDoctorAppointmentPerDay(LocalDate tempDate, int doctorId) {

		return appointmentDAO.getDoctorAppointmentPerDay(tempDate, doctorId);
	}

	@Override
	public List<Appointment> getAppointments() {

		return appointmentDAO.getAppointments();

	}

	@Override
	public void acceptAppointment(int appointID) {
		Appointment appointment = appointmentDAO.getAppointment(appointID);
		appointment.setStatus(AppointStatus.ACCEPTED.getAppointStatus());
	}

	@Override
	public void declineAppointment(int appointID) {
		Appointment appointment = appointmentDAO.getAppointment(appointID);
		appointment.setStatus(AppointStatus.DECLINED.getAppointStatus());
	}

	@Override
	public Appointment getAppointment(int patientID, int scheduleId) {
		return appointmentDAO.getAppointmentByPatientId(patientID, scheduleId);
	}

	@Override
	public Appointment getAppointmen(int appointId) {
		return appointmentDAO.getAppointment(appointId);
	}

	@Override
	public void editAppointment(Appointment appoint) {
		Appointment appointment = appointmentDAO.getAppointment(appoint.getAppointmentID());
		if (appointment != null) {
			appointment.setReason(appoint.getReason());
		}
	}

	@Override
	public void deleteAppointment(int appointmentId) {
		appointmentDAO.deleteAppointment(appointmentId);
	}

	@Override
	public List<Appointment> appointmentListPerDoctor(int staffID) {
		return appointmentDAO.appointmentListPerDoctor(staffID);
	}

}
