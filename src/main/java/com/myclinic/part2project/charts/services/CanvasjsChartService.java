//CanvasjsChartService.java
package com.myclinic.part2project.charts.services;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartService {

	List<List<Map<Object, Object>>> getCanvasjsChartData();
	List<List<Map<Object, Object>>> getPieCanvasjsChartData();
	List<List<Map<Object, Object>>> getPieGenderCanvasjsChartData();
}


//CanvasjsChartServiceImpl.java
