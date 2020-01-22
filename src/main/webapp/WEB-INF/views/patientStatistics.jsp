
<%@ include file="commons/navigation.jspf"%>
<div class="container" style="margin-top: 20px;">
	<div class="row col-md-11">
		<ol>
			<li style="display: inline;"><Strong>System reports: </Strong></li>
			<li style="display: inline;"><a class="btn btn-default btn-xs"
				href="<c:url value='/paymentReports'/>"> Payment report</a></li>
			<li style="display: inline;"><a class="btn btn-default btn-xs"
				href="<c:url value='/patientReports'/>">Patient report</a></li>
			<li style="display: inline;"><a class="btn btn-default btn-xs"
				href="<c:url value='/staffReports'/>">Staff report</a></li>
		</ol>
		<hr class="btn-primary">
		<div class="col-md-5">
			<div id="chartContainer" style="height: 200px; width: 100%;"></div>
		</div>

		<div class="col-md-5">
			<div id="chartContainerPie" style="height: 200px; width: 100%;"></div>
		</div>
	</div>
	<br>
	<div class="row col-md-11">
		<br>
		<div class="col-md-5">
			<div id="chartContainerPieGender" style="height: 200px; width: 100%;"></div>
		</div>
	</div>

</div>


<%@ include file="commons/footer.jspf"%>
<script type="text/javascript">
	window.onload = function() {

		var dps = [ [] ];
		var chart = new CanvasJS.Chart("chartContainerPie", {
			exportEnabled : true,
			animationEnabled : true,
			theme : "light1", // "light1", "dark1", "dark2"
			title : {
				text : "CLINIC VISITORS"
			},
			subtitles : [ {
				text : "Age Groups of Patient Visitors"
			} ],
			data : [ {
				type : "pie",
				yValueFormatString : "#,##0\"%\"",
				indexLabel : "{label} - {y}",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${dataPointsListPie}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chart.render();

		var dps = [ [] ];
		var chart = new CanvasJS.Chart("chartContainerPieGender", {
			exportEnabled : true,
			animationEnabled : true,
			theme : "light1", // "light1", "dark1", "dark2"
			title : {
				text : "CLINIC VISITORS"
			},
			subtitles : [ {
				text : "Patient visit the clinic based on gender"
			} ],
			data : [ {
				type : "pie",
				yValueFormatString : "#,##0\"%\"",
				indexLabel : "{label} - {y}",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${dataPointsListPieGender}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chart.render();

		var dps = [ [] ];
		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled : true,
			theme : "light1",// "dark1", "dark2"
			title : {
				text : "Clinic Data"
			},
			subtitles : [ {
				text : "Based on number of users in the database"
			} ],
			axisY : {
				title : "User numbers (in numbers)",
				suffix : "users",
				includeZero : false
			},
			axisX : {
				title : "Clinic users"
			},
			data : [ {
				type : "column",
				yValueFormatString : "#,##0 users",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chart.render();

	}
</script>

</body>
</html>