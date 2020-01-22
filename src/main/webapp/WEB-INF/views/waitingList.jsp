<%@ include file="commons/navigation.jspf"%>
<br>

<div class="container">
	<div class="table-agile-info">
		<div style="margin-left: 250px; width: 70%;">
			<c:if test="${addSuccess}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong> Hello!</strong> Patient successfully added to waiting list
				</div>
			</c:if>

		</div>
		<div style="margin-left: 250px; width: 70%;">
			<c:if test="${paySuccess}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong> Hello!</strong> Payment successful
				</div>
			</c:if>

		</div>
		<div>
			<h3 style="text-align: center;">Patient WaitingList</h3>
			<hr class="btn-primary">

			<div class="table-responsive text-nowrap ">
				<table id="myTable" class="table table-striped  table-bordered ">
					<thead>
						<tr>
							<th class="th-sm">Patient ID</th>
							<th class="th-sm">Name</th>
							<th class="th-sm">Gender</th>
							<th class="th-sm">Marital Status</th>
							<th class="th-sm">Date of Birth</th>
							<security:authorize
								access="hasRole('RECEPTIONIST') or hasRole('DOCTOR') or hasRole('NURSE') or hasRole('PHARMACIST')">
								<th class="th-sm">Action</th>
							</security:authorize>
						</tr>
					</thead>
					<!-- loop over and print our patients -->
					<c:forEach var="tempRecord" items="${patients}">

						<!-- construct an "treat patient" link with patient id -->
						<c:url var="prescibeLink" value="/prescribe">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>

						<!-- construct an "treat patient" link with patient id -->
						<c:url var="payLink" value="/showPaymentForm">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>

						<!-- construct an "close record" link with patient id -->
						<c:url var="closeLink" value="/closeRecord">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>
						<!-- construct an "close record" link with patient id -->
						<c:url var="issueLink" value="/issueDrugs">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>

						<!-- construct an "treat patient" link with patient id -->
						<c:url var="treatLink" value="/showTreatmentForm">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>

						<!-- construct an "delete" link with patient id -->
						<c:url var="viewLink" value="/viewPatientPreviousRecord">
							<c:param name="patientId" value="${tempRecord.patient.patientID}" />
						</c:url>

						<!-- construct an "delete" link with patient id -->
						<c:url var="basicLink" value="/showBasicTestForm">
							<c:param name="recordId" value="${tempRecord.recordID}" />
						</c:url>

						<tr>
							<td>${tempRecord.patient.patientID }</td>
							<td>${tempRecord.patient.firstName}
								${tempRecord.patient.lastName}</td>
							<td>${tempRecord.patient.gender.genderType }</td>
							<td>${tempRecord.patient.maritalStatus.marital }</td>
							<td>${tempRecord.patient.dob}</td>

							<security:authorize access="hasRole('RECEPTIONIST')">
								<td><a href="${payLink}" class="btn btn-primary btn-xs">Make
										Payment</a></td>
							</security:authorize>
							<security:authorize
								access="hasRole('DOCTOR') or hasRole('NURSE')">
								<td><a href="${treatLink}" class="btn btn-success btn-xs">Treat
										Patient</a> |<a href="${viewLink}" class="btn btn-primary btn-xs">Previous
										visit</a> |<a href="${prescibeLink}"
									class="btn btn-default btn-xs">Prescribe</a>| <a
									class="btn btn-info btn-xs" href=" ${basicLink }">Add
										BasicTest</a> <a href="${closeLink}"
									class="btn btn-success btn-xs">Close</a></td>
							</security:authorize>
							<security:authorize access="hasRole('PHARMACIST')">
								<td><a href="${closeLink}" class="btn btn-success btn-xs">Close</a>|<a
									href="${issueLink}" class="btn btn-info btn-xs">view
										prescription</a></td>
							</security:authorize>

						</tr>

					</c:forEach>

				</table>
			</div>
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