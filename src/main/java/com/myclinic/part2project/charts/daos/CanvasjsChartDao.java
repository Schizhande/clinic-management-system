package com.myclinic.part2project.charts.daos;
 
import java.util.List;
import java.util.Map;
 
public interface CanvasjsChartDao {
 
	List<List<Map<Object, Object>>> getCanvasjsChartData();
	
	List<List<Map<Object, Object>>> getPieCanvasjsChartData();
 
	List<List<Map<Object, Object>>> getPieGenderCanvasjsChartData();
}
 
//CanvasjsChartDaoImpl.java
