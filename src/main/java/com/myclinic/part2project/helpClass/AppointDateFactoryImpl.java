package com.myclinic.part2project.helpClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.stereotype.Component;

@Component
public class AppointDateFactoryImpl implements AppointDateFactory {

	@Override
	public LocalDate getAppointmentDate(LocalDate tempDate, String day) {
		if (day.equalsIgnoreCase("Monday")) {
			return tempDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}
		if (day.equalsIgnoreCase("Tuesday")) {
			return tempDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		}
		if (day.equalsIgnoreCase("Wednesday")) {
			return tempDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
		}
		if (day.equalsIgnoreCase("Thursday")) {
			return tempDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		}
		if (day.equalsIgnoreCase("Friday")) {
			return tempDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		}
		return null;
	}

}
