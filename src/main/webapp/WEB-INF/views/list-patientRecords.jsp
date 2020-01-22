<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center;">Patient Records</h3>
		<hr class="btn-primary">

		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Patient ID</th>
						<th class="th-sm">Record ID</th>
						<th class="th-sm">Name</th>
						<th class="th-sm">Visit Date</th>
						<th class="th-sm">Action</th>

					</tr>
				</thead>
				<!-- loop over and print our patients -->
				<c:forEach var="tempRecord" items="${records}">
					<!-- construct an "treat patient" link with patient id -->
					<c:url var="waitingLink" value="/reAddToWaiting">
						<c:param name="recordId" value="${tempRecord.recordID}" />
					</c:url>

					<!-- construct an "delete" link with patient id -->
					<c:url var="viewLink" value="/showPatientRecord">
						<c:param name="recordId" value="${tempRecord.recordID}" />
					</c:url>

					<tr>
						<td>${tempRecord.patient.patientID }</td>
						<td>${tempRecord.recordID}</td>
						<td>${tempRecord.patient.firstName}
							${tempRecord.patient.lastName}</td>
						<td>${tempRecord.visitDate}</td>

						<td><security:authorize
								access="hasRole('DOCTOR') or hasRole('NURSE') or hasRole('ADMIN') or hasRole('PHARMACIST')">
								<a href="${viewLink}" class="btn btn-info btn-xs">View</a>  |  <a
									href="${waitingLink}" class="btn btn-warning btn-xs">Add to
									waitingList</a>
							</security:authorize></td>

					</tr>

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