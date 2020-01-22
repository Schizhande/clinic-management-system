<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center; margin-left: 200px;">Patient list</h3>


		<security:authorize
			access="hasRole('ADMIN') or hasRole('RECEPTIONIST')">
			<input type="button" value="Add Patient"
				onclick="window.location.href='patientForm'; return false;"
				class="btn btn-primary" />
		</security:authorize>

		<hr class="btn-primary">

		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Patient ID</th>
						<th class="th-sm">Name</th>
						<th class="th-sm">Gender</th>
						<th class="th-sm">Joined Date</th>
						<th class="th-sm">Marital Status</th>
						<th class="th-sm">Date of Birth</th>
						<th class="th-sm">Action</th>

					</tr>
				</thead>
				<!-- loop over and print our patients -->
				<c:forEach var="tempPatient" items="${patients}">

					<!-- construct an "update" link with patient id -->
					<c:url var="updateLink" value="/showFormForPatientUpdate">
						<c:param name="patientId" value="${tempPatient.patientID}" />
					</c:url>

					<!-- construct an "delete" link with patient id -->
					<c:url var="deleteLink" value="/deletePatient">
						<c:param name="patientId" value="${tempPatient.patientID}" />
					</c:url>

					<!-- construct an "delete" link with patient id -->
					<c:url var="viewLink" value="/viewPatientProfile">
						<c:param name="patientId" value="${tempPatient.patientID}" />
					</c:url>

					<tr class="th-sm">
						<td class="th-sm">${tempPatient.patientID }</td>
						<td class="th-sm">${tempPatient.firstName}
							${tempPatient.lastName}</td>
						<td class="th-sm">${tempPatient.gender.genderType }</td>
						<td class="th-sm">${tempPatient.joinedDate }</td>
						<td class="th-sm">${tempPatient.maritalStatus.marital }</td>
						<td>${tempPatient.dob}</td>

						<td class="th-sm">
							<!-- display the update link --> <security:authorize
								access="hasRole('ADMIN') or hasRole('RECEPTIONIST')">
								<a href="${updateLink}" class="btn btn-info btn-xs">Update</a> |
						</security:authorize> <security:authorize access="hasRole('ADMIN')">
								<a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this patient?'))) return false"
									class="btn btn-danger btn-xs">Delete</a> |
										</security:authorize> <a href="${viewLink}" class="btn btn-primary btn-xs">View</a>
						</td>

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









