package com.myclinic.part2project.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").access("hasRole('PATIENT') or hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('RECEPTIONIST') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/getDoctorSchedule","/changePassword").access("hasRole('PATIENT') or hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('RECEPTIONIST') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/appointmentForm").access("hasRole('PATIENT')")
				.antMatchers("/addFeedback").access("hasRole('PATIENT')")
				.antMatchers("/checkAppointmentStatus").access("hasRole('PATIENT')")
				.antMatchers("/editAppointment").access("hasRole('PATIENT')")
				.antMatchers("/updateAppointment").access("hasRole('PATIENT')")
				.antMatchers("/saveAppointment").access("hasRole('PATIENT')")
				.antMatchers("/viewPatProfile").access("hasRole('PATIENT')")
				.antMatchers("/saveFeedBack").access("hasRole('PATIENT')")
				.antMatchers("/deletePost").access("hasRole('PATIENT')")
				.antMatchers("/cancelAppointment").access("hasRole('PATIENT')")
				.antMatchers("/viewRecordHistory").access("hasRole('PATIENT')")
				.antMatchers("/viewStaffProfile").access("hasRole('NURSE') or hasRole('RECEPTIONIST') or hasRole('DOCTOR') or hasRole('PHARMACIST')")
				.antMatchers("/listAppointment").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('NURSE')")	
				.antMatchers("/acceptAppointment").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('NURSE')")	
				.antMatchers("/declineAppointment").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('NURSE')")
				.antMatchers("/cancelAppointment").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('NURSE') or hasRole('PATIENT')")	
				.antMatchers("/patientSearch").access("hasRole('RECEPTIONIST')")	
				.antMatchers("/searchPatient").access("hasRole('RECEPTIONIST')")
				.antMatchers("/saveOrUpdatePatient").access("hasRole('RECEPTIONIST') or hasRole('ADMIN')")	
				.antMatchers("/patientForm").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') ")	
				.antMatchers("/listPatients").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('NURSE') or hasRole('DOCTOR') or hasRole('PHARMACIST')")	
				.antMatchers("/showFormForPatientUpdate").access("hasRole('RECEPTIONIST') or hasRole('ADMIN')")	
				.antMatchers("/viewPatientProfile").access("hasRole('RECEPTIONIST') or hasRole('ADMIN') or hasRole('PHARMACIST') or hasRole('NURSE') or hasRole('DOCTOR')")
				.antMatchers("/showPaymentForm").access("hasRole('RECEPTIONIST') or hasRole('PHARMACIST')")	
				.antMatchers("/savePayment").access("hasRole('RECEPTIONIST') or hasRole('PHARMACIST')")	
				.antMatchers("/addToWaitingList").access("hasRole('RECEPTIONIST') or hasRole('DOCTOR') ")	
				.antMatchers("/getWaitingList").access("hasRole('ADMIN') or hasRole('PHARMACIST') or hasRole('DOCTOR') or hasRole('RECEPTIONIST') or hasRole('NURSE')")
				.antMatchers("/saveOrUpdateStaff").access("hasRole('ADMIN')")
				.antMatchers("/saveOrUpdateStaff").access("hasRole('ADMIN')")
				.antMatchers("/viewPatientPreviousRecord").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/viewAllPatientRecords").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/addComment").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/saveComment").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/showPatientRecord").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/showBasicTestForm").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/saveBasicTest").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/saveDrugPlusPrescript").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/removeDrug").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/showTreatmentForm").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/treatPatient").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/closeRecord").access("hasRole('DOCTOR') or hasRole('NURSE')")
				.antMatchers("/prescribePatient").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/prescribe").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')")
				.antMatchers("/appointmentListPerDoctor").access("hasRole('DOCTOR')")
				.antMatchers("/showDrugForm").access("hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/getStock").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/addStock").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/saveStock").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/showFormForStockUpdate").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/deleteStock").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/issueDrugs").access("hasRole('PHARMACIST')")
				.antMatchers("/updateDrug").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/getDrugs").access("hasRole('PHARMACIST') or hasRole('ADMIN')")
				.antMatchers("/viewPie", "/viewFeedback").access("hasRole('ADMIN')")
				.antMatchers("/getReportPage", "/listPayments").access("hasRole('ADMIN')")
				.antMatchers("/purchaseMedicine", "/payMedicineBill").access("hasRole('PHARMACIST')")
				.antMatchers("/listSales").access("hasRole('PHARMACIST') or hasRole('ADMIN')" )
				.and().formLogin().loginPage("/login")
				.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
