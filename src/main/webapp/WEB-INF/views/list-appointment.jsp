<%@ include file="commons/navigation.jspf"%>

<br>
<c:if test="${deleteSuccess}">
	<div class="alert alert-success row col-sm-offset-1 col-xs-8">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Hello! ${name }</strong> Your appointment had successfully
		cancelled
	</div>
</c:if>
<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center; ">Appointment
			list</h3>
		<security:authorize access=" hasRole('RECEPTIONIST')">
			<input type="button" value="Book appointment"
				onclick="window.location.href='appointForm'; return false;"
				class="btn btn-primary" />
		</security:authorize>
		<hr class="btn-primary">

		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Appointment Id</th>
						<th class="th-sm">Appointment Date</th>
						<th class="th-sm">Appointment Time</th>
						<th class="th-sm">Reason</th>
						<th class="th-sm">Status</th>
						<security:authorize
							access="hasRole('ADMIN') or hasRole('RECEPTIONIST')">
							<th class="th-sm">Doctor Name</th>
						</security:authorize>
						<th class="th-sm">Patient Name</th>
						<security:authorize
							access=" hasRole('RECEPTIONIST') or hasRole('ADMIN')">
							<th class="th-sm">Action</th>
						</security:authorize>


					</tr>
				</thead>
				<!-- loop over and print our appointments -->
				<c:forEach var="tempApp" items="${appointment}">
					<!-- construct an "delete" link with patient id -->
					<c:url var="viewLink" value="/viewAppointment">
						<c:param name="appointmentId" value="${tempApp.appointmentID}" />
					</c:url>

					<tr>
						<td>${tempApp.appointmentID }</td>
						<td>${tempApp.appointmentDate}</td>
						<td>${tempApp.appointmentTime}</td>
						<td>${tempApp.reason }</td>
						<td>${tempApp.status }</td>
						<security:authorize
							access="hasRole('ADMIN') or hasRole('RECEPTIONIST')">
							<td>${tempApp.doctor.lastName }</td>
						</security:authorize>
						<td>${tempApp.patient.lastName}</td>
						<security:authorize
							access="hasRole('ADMIN') or hasRole('RECEPTIONIST')">
							<td><a href="${viewLink}" class="btn btn-info btn-xs">view</a>
							</td>
						</security:authorize>
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