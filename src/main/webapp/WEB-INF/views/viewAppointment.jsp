<%@ include file="commons/navigation.jspf"%>
<br>
<br>
<div id="content" class="row col-sm-offset-1  col-xs-8">
	<h4 style="text-align: center;">Appointment ID:
		${appointment.appointmentID }</h4>
	<table class="table table-bordered  ">

		<tr>
			<th>Appointment date</th>
			<td>${appointment.appointmentDate}</td>
		</tr>
		<tr>
			<th>Appointment time</th>
			<td>${appointment.appointmentTime }</td>
		</tr>
		<tr>
			<th>Appointment Status</th>
			<td>${appointment.status}</td>
		</tr>
		<tr>
			<th>Reason</th>
			<td>${appointment.reason }</td>
		</tr>

		<tr>
			<th>Action</th>
			<c:url var="acceptLink" value="/acceptAppointment">
				<c:param name="appointmentId" value="${appointment.appointmentID}" />
			</c:url>

			<!-- construct an "update" link with appointment id -->
			<c:url var="deleteLink" value="/deleteAppointment">
				<c:param name="appointmentId" value="${appointment.appointmentID}" />
			</c:url>

			<!-- construct an "delete" link with appointment id -->
			<c:url var="declineLink" value="/declineAppointment">
				<c:param name="appointmentId" value="${appointment.appointmentID}" />
			</c:url>

			<td><a href="${acceptLink}" class="btn btn-success btn-xs">Accept</a>
				| <a href="${declineLink}"
				onclick="if (!(confirm('Are you sure you want to decline this appointment?'))) return false"
				class="btn btn-warning btn-xs">Decline</a> | <a href="${deleteLink}"
				onclick="if (!(confirm('Are you sure you want to remove this appointment, remove if you are adding patient to the waiting list?'))) return false"
				class="btn btn-danger btn-xs">Remove</a></td>
		</tr>
	</table>
</div>

<%@ include file="commons/footer.jspf"%>

</body>
</html>