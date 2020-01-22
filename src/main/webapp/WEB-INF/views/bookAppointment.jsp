<%@ include file="commons/navigation.jspf"%>
<br>

<br>
<div id="registrationbox" class="mainbox col-md-6   col-md-offset-2">


	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Appointment booking form</div>
		</div>
		<br> <br>
		<div class="container">
			<c:choose>
				<c:when test="${edit}">
					<c:url var="myurl" value="updateAppointment" />
				</c:when>

				<c:otherwise>
					<c:url var="myurl" value="saveAppointment" />
				</c:otherwise>
			</c:choose>

			<form:form action="${myurl}" modelAttribute="appointment"
				method="POST" class="col-md-12">
				<fieldset>
					<form:hidden path="appointmentID" />
					<form:hidden path="doctor.staffID" />
					<form:hidden path="appointmentDate" />
					<form:hidden path="appointmentTime" />
					<form:hidden path="schedule.scheduleID" />
				</fieldset>
				<div class="form-group row">
					<form:label path="reason" class="col-sm-2 col-form-label">Description</form:label>
					<div class="col-sm-4">
						<form:textarea id="inputBorder" class="form-control"
							placeholder="Explain your condition" path="reason" />
						<form:errors path="reason" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
				<div class="col-sm-8">
				</div>
					<div class="col-sm-8">
						<button type="submit" class="btn btn-info">Save</button>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>
</body>
</html>