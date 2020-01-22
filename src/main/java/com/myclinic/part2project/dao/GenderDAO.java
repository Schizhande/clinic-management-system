package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Gender;
import com.myclinic.part2project.model.MaritalStatus;

public interface GenderDAO {

	List<Gender> getGenders();

	List<MaritalStatus> getMaritals();

}
