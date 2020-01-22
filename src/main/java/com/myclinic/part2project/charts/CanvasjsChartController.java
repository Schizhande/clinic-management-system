package com.myclinic.part2project.charts;
 
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.myclinic.part2project.charts.services.CanvasjsChartService;
 
@Controller
public class CanvasjsChartController {
 
	@Autowired
	private CanvasjsChartService canvasjsChartService;
 
	@GetMapping(value="/canvasjschart")
	public String springMVC(ModelMap modelMap) {
		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
		modelMap.addAttribute("dataPointsList", canvasjsDataList);
		return "charts/adminDash";
	}
	
	@GetMapping(value="/viewPie")
	public String springMVCPie(ModelMap modelMap) {
		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
		List<List<Map<Object, Object>>> canvasjsDataListPie = canvasjsChartService.getPieCanvasjsChartData();
		modelMap.addAttribute("dataPointsListPie", canvasjsDataListPie);
		List<List<Map<Object, Object>>> canvasjsDataListPieGender = canvasjsChartService.getPieGenderCanvasjsChartData();
		modelMap.addAttribute("dataPointsListPieGender", canvasjsDataListPieGender);
		modelMap.addAttribute("dataPointsList", canvasjsDataList);
		return "patientStatistics";
	}
 
}                        