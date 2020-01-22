package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Gender;
import com.myclinic.part2project.model.MaritalStatus;

public interface GenderService {

	List<Gender> getGenders();

	List<MaritalStatus> getMaritals();

}
