//CanvasjsChartData.java
package com.myclinic.part2project.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasjsChartData {

	static Map<Object,Object> map = null;
	static List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
	static List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
	
	static {
		map = new HashMap<Object,Object>(); map.put("label", "Receptionist"); map.put("y", 30);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "Patient"); map.put("y", 100);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "Nurses"); map.put("y", 80);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "Pharmacist"); map.put("y", 56);dataPoints1.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "Doctors"); map.put("y", 40);dataPoints1.add(map);
		
		list.add(dataPoints1);
	}

	public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
		return list;
	}
	
	static Map<Object,Object> mapPie = null;
	static List<List<Map<Object,Object>>> listPie = new ArrayList<List<Map<Object,Object>>>();
	static List<Map<Object,Object>> dataPoints2= new ArrayList<Map<Object,Object>>();
	
	static {
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "18 yrs and Under"); mapPie.put("y", 7);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "19 to 32 yrs"); mapPie.put("y", 51);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "32 to 45 yrs"); mapPie.put("y", 12);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "45 to 60 yrs"); mapPie.put("y", 17);dataPoints2.add(mapPie);
		mapPie = new HashMap<Object,Object>(); mapPie.put("label", "60 yrs and over"); mapPie.put("y", 13);dataPoints2.add(mapPie);
		
		listPie.add(dataPoints2);
	}
 
	public static List<List<Map<Object, Object>>> getPieCanvasjsDataList() {
		return listPie;
	}
}                        