package com.myclinic.part2project.helpClass;

import java.time.LocalDate;

public interface AppointDateFactory {
	LocalDate getAppointmentDate(LocalDate now, String day);

}
