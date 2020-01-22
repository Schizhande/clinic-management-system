<%@ include file="commons/navigation.jspf"%>
<br>
<div class="container">
	<div class="table-agile-info">
		<div style="text-align: center;">
			<c:if test="${patientRecords.isEmpty()}">
				<div class="alert alert-info">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Hello! <security:authentication
							property="principal.username" /></strong> No record yet you can visit to
					the clinic and examined by the doctor
				</div>
			</c:if>
		</div>
		<h3 style="text-align: center; margin-left: 150px;">Patient's
			Previous records</h3>
		<h3>Patient name: ${patientName} ${records.patient.firstName }</h3>
		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table  table-bordered ">
				<thead>
					<tr>
						<th>Date</th>
						<th>Doctor Name</th>
						<th>Basic Test</th>
						<th>Symptoms</th>
						<th>Disease</th>
						<th>Drug</th>
						<th>Doctor Comment</th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach var="records" items="${patientRecords}">
					<tbody>
						<tr>
							<td>${records.visitDate }</td>
							<td><c:forEach var="doctors" items="${records.examinedBy}">
								${doctors.getDoctorOrNurse().getLastName()} <br>
								</c:forEach></td>
							<td>${records.basictest.weight }<br>
								${records.basictest.temperature}<br> ${records.basictest.bp }
							</td>
							<td><c:forEach var="symptoms"
									items="${records.recordSymptom}">
								${symptoms.symptom.symptomName} <br>
								</c:forEach></td>
							<td><c:forEach var="diseases"
									items="${records.getRecordDisease()}">
								${diseases.getDisease().getDiseaseName()} <br>
								</c:forEach></td>
							<td><c:forEach var="presc"
									items="${records.getPrescription()}">
								${presc.getDrug().getName()} <br>
								</c:forEach></td>
							<td>${records.comment.description }</td>
							<c:url var="preLink" value="/viewPrescription">
								<c:param name="recordId" value="${records.recordID}" />
							</c:url>
							<td><a href="${preLink}" class="btn btn-primary btn-xs">View
									Prescription</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>
<script>
	$(document).ready(function() {
		$("#myTable").DataTable({
			"lengthMenu" : [ [ 5, 10, 15, 20, -1 ], [ 5, 10, 15, 20, "All" ] ],
			"ordering" : false,
			stateSave : true
		});
	});
</script>
</body>
</html>