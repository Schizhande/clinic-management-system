<%@ include file="commons/navigation.jspf"%>
<br>
<br>
<div id="registrationbox" class="mainbox col-md-9  col-md-offset-1">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Your appointment details</div>
		</div>
		<br> <br>
		<div class="container">
			<div class="container">
				<c:if test="${appointmentSuccess}">
					<div class="alert alert-success row col-sm-offset-1 col-xs-8">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Hello! ${name }</strong> Your appointment had successfully
						send
					</div>
				</c:if>
				<c:if test="${editSuccess}">
					<div class="alert alert-success row col-sm-offset-1 col-xs-8">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Hello! ${name }</strong> Your appointment had successfully
						updated
					</div>
				</c:if>
			</div>
			<c:choose>
				<c:when test="${appointment!=null}">
					<div class="row col-sm-offset-1  col-xs-8">
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
								<th>Appointment Reason</th>
								<td>${appointment.reason}</td>
							</tr>

						</table>
					</div>


					<c:if test="${appointment.status=='Pending'}">
						<div class="row col-sm-offset-1 col-xs-8">
							<p>
								Edit appointment click <a
									href="editAppointment?appointmentId=${appointment.appointmentID }">
									<i class="glyphicon glyphicon-pencil"></i>
								</a>
							</p>
							<p>
								Cancel appointment click <a 	onclick="if (!(confirm('Are you sure you want to delete this appoitment?'))) return false"
									href="cancelAppointment?appointmentId=${appointment.appointmentID }">
									<i class="glyphicon glyphicon-trash"></i>
								</a>
							</p>
						</div>

					</c:if>

				</c:when>

				<c:otherwise>

					<div class="alert alert-info row col-sm-offset-1 col-xs-8">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Hello! ${name }</strong> No appointment booked
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>
</body>
</html>