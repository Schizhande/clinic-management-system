package com.myclinic.part2project.charts.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.charts.CanvasjsChartData;

@Repository
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select count(*) from Patient p ");
		Iterator count = query.iterate();
		Object numberOfPatient = count.next();

		Query query1 = currentSession.createQuery("select count(*) from Staff s WHERE s.jobTitle.title='DOCTOR' ");
		Iterator count1 = query1.iterate();
		Object numberOfDoctors = count1.next();

		Query query2 = currentSession.createQuery("select count(*) from Staff s WHERE s.jobTitle.title='NURSE' ");
		Iterator count2 = query2.iterate();
		Object numberOfNurses = count2.next();

		Query query3 = currentSession.createQuery("select count(*) from Staff s WHERE s.jobTitle.title='PHARMACIST' ");
		Iterator count3 = query3.iterate();
		Object numberOfPharmacist = count3.next();

		Query query4 = currentSession
				.createQuery("select count(*) from Staff s WHERE s.jobTitle.title='RECEPTIONIST' ");
		Iterator count4 = query4.iterate();
		Object numberOfReceptionist = count4.next();

		map = new HashMap<Object, Object>();
		map.put("label", "Receptionist");
		map.put("y", numberOfReceptionist);
		dataPoints1.add(map);
		map = new HashMap<Object, Object>();
		map.put("label", "Patient");
		map.put("y", numberOfPatient);
		dataPoints1.add(map);
		map = new HashMap<Object, Object>();
		map.put("label", "Nurses");
		map.put("y", numberOfNurses);
		dataPoints1.add(map);
		map = new HashMap<Object, Object>();
		map.put("label", "Pharmacist");
		map.put("y", numberOfPharmacist);
		dataPoints1.add(map);
		map = new HashMap<Object, Object>();
		map.put("label", "Doctors");
		map.put("y", numberOfDoctors);
		dataPoints1.add(map);

		list.add(dataPoints1);
		return list;
	}

	@Override
	public List<List<Map<Object, Object>>> getPieCanvasjsChartData() {

		return CanvasjsChartData.getPieCanvasjsDataList();
	}

	@Override
	public List<List<Map<Object, Object>>> getPieGenderCanvasjsChartData() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query1 = currentSession.createQuery("select count(*) from Patient s WHERE s.gender.genderType='MALE' ");
		Iterator count1 = query1.iterate();
		Object numberOfMales = count1.next();

		Query query2 = currentSession.createQuery("select count(*) from Patient s WHERE s.gender.genderType='FEMALE' ");
		Iterator count2 = query2.iterate();
		Object numberOfFemales = count2.next();
		Map<Object, Object> mapPie = null;
		List<List<Map<Object, Object>>> listPie = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints2 = new ArrayList<Map<Object, Object>>();
		Long m = new Long((long) numberOfMales);
		Double mm = m.doubleValue();
		Long f = new Long((long) numberOfFemales);
		System.out.println("" + f);
		Double ff = f.doubleValue();

		Double mmm = (mm / (mm + ff)) * 100;
		Double fff = (ff / (mm + ff)) * 100;
		System.out.println("" + mm);
		System.out.println("" + ff);
		mapPie = new HashMap<Object, Object>();
		mapPie.put("label", "Female patient");
		mapPie.put("y", fff);
		dataPoints2.add(mapPie);
		mapPie = new HashMap<Object, Object>();
		mapPie.put("label", "Male patient");
		mapPie.put("y", mmm);
		dataPoints2.add(mapPie);
		listPie.add(dataPoints2);
		return listPie;
	}

}