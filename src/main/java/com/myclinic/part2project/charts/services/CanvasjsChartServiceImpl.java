package com.myclinic.part2project.charts.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.charts.daos.CanvasjsChartDao;
import com.myclinic.part2project.dao.PatientDAO;
import com.myclinic.part2project.dao.StaffDAO;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Staff;
import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;

@Service
@Transactional
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

	@Autowired
	private CanvasjsChartDao canvasjsChartDao;

	@Autowired
	private PatientDAO patientDAO;

	@Autowired
	private StaffDAO staffDao;

	 

	public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
		this.canvasjsChartDao = canvasjsChartDao;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		return canvasjsChartDao.getCanvasjsChartData();
	}

	@Override
	public List<List<Map<Object, Object>>> getPieCanvasjsChartData() {
		Map<Object,Object> mapPie = null;
		List<List<Map<Object,Object>>> listPie = new ArrayList<List<Map<Object,Object>>>();
		List<Map<Object,Object>> dataPoints2= new ArrayList<Map<Object,Object>>();
		float less19=0.0f;
		float wok=0.0f;
		float le=0.0f;
		for(Patient pat: patientDAO.getPatients()){
			
			 if(pat.getDob().until(LocalDate.now(), YEARS)<19){
				 less19++; 
			 }
			 
			 if((pat.getDob().until(LocalDate.now(), YEARS)>18)&&(pat.getDob().until(LocalDate.now(), YEARS)<60)){
				 wok++; 
			 }
			 
			 if(pat.getDob().until(LocalDate.now(), YEARS)>59){
				 le++; 
			 }
			 
		}
		less19=(less19/(float)patientDAO.getPatients().size())*100;
		wok=(wok/(float)patientDAO.getPatients().size())*100;
		le=(le/(float)patientDAO.getPatients().size())*100;
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "18 yrs and Under"); mapPie.put("y", less19);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "19 to 60 yrs"); mapPie.put("y", wok);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "60 yrs and over"); mapPie.put("y", le);dataPoints2.add(mapPie);
		
		listPie.add(dataPoints2);
		return listPie;
	}

	@Override
	public List<List<Map<Object, Object>>> getPieGenderCanvasjsChartData() {
		return canvasjsChartDao.getPieGenderCanvasjsChartData();
	}

}