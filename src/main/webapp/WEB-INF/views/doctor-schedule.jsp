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
		<h3 style="text-align: center; margin-left: 200px;">Doctor
			Schedules</h3>

		<security:authorize access="hasRole('ADMIN')">
			<input type="button" value="Add Schedule"
				onclick="window.location.href='createSchedule'; return false;"
				class="btn btn-primary" />


			<hr class="btn-primary">
		</security:authorize>
		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">ID</th>
						<th class="th-sm">Day</th>
						<th class="th-sm">Start Time</th>
						<th class="th-sm">End Time</th>
						<th class="th-sm">Doctor Name</th>
						<th class="th-sm">Specialty</th>
						<security:authorize access="hasRole('ADMIN')">
							<th class="th-sm">Action</th>
						</security:authorize>
						<security:authorize access="hasRole('PATIENT')">
							<th class="th-sm">Action</th>
						</security:authorize>
					</tr>
				</thead>

				<c:forEach var="schedule" items="${schedules}">

					<c:url var="bookLink" value="/appointmentForm">
						<c:param name="scheduleId" value="${schedule.scheduleID}" />
						<c:param name="doctorId" value="${schedule.doctor.staffID}" />
					</c:url>

					<c:url var="deleteLink" value="/deleteSchedule">
						<c:param name="scheduleId" value="${schedule.scheduleID}" />
					</c:url>

					<c:url var="statusLink" value="/checkAppointmentStatus">
						<c:param name="scheduleId" value="${schedule.scheduleID}" />
					</c:url>

					<c:url var="updateLink" value="/updateSchedule">
						<c:param name="scheduleId" value="${schedule.scheduleID}" />
					</c:url>

					<tr>
						<td>${schedule.scheduleID }</td>
						<td>${schedule.day}</td>
						<td>${schedule.startTime}</td>
						<td>${schedule.endTime }</td>
						<c:choose>
							<c:when
								test="${schedule.doctor.gender.genderType.equalsIgnoreCase('male')}">
								<td>Mr ${schedule.doctor.lastName }</td>
							</c:when>
							<c:otherwise>
								<td>Mrs/Miss/Ms ${schedule.doctor.lastName }</td>
							</c:otherwise>
						</c:choose>
						<td>${schedule.getDoctor().getSpecialty().getName()}</td>
						<security:authorize access="hasRole('PATIENT')">
							<td><a href="${bookLink}" class="btn btn-primary btn-xs">Book</a>
								<a href="${statusLink}" class="btn btn-primary btn-xs">Check
									Appointment Status</a></td>
						</security:authorize>
						<security:authorize access="hasRole('ADMIN')">
							<td>| <a href="${deleteLink}" class="btn btn-danger btn-xs">Delete</a>|
								<a href="${updateLink}" class="btn btn-success btn-xs">Update</a></td>
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