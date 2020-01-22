<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<br>
<br>
<div class="container" style="margin-left: 250px; width: 70%;">
	<c:if test="${doctors.isEmpty()}">
		<div class="alert alert-warning">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello! </strong> Currently no doctors in the system, you have
			to add doctor first
		</div>
	</c:if>

	<div id="registrationbox" class="mainbox col-md-8  col-md-offset-1">

		<div class="panel panel-info">

			<div class="panel-heading">
				<div class="panel-title">Add Doctor Schedule</div>
			</div>
			<br> <br>
			<div class="container">
				<form:form action="saveSchedule" modelAttribute="schedule"
					method="POST" class="col-md-12">

					<form:hidden path="scheduleID" />

					<div class=" form-group row">
						<form:label class="col-sm-2  col-form-label" path="doctor.staffID">Doctor</form:label>
						<div class="col-sm-4">
							<form:select id="inputBorder" class="form-control"
								path="doctor.staffID">
								<option value="aa">Select a type</option>
								<c:forEach items="${doctors}" var="doctor">
									<option value="${doctor.staffID}">${doctor.lastName}</option>
								</c:forEach>
							</form:select>
							<form:errors path="doctor.staffID" cssClass="error"></form:errors>
						</div>
					</div>

					<div class="form-group row">
						<form:label path="day" class="col-sm-2   col-form-label">Day Of Week</form:label>
						<div class="col-sm-4">
							<form:select id="inputBorder" path="day" class="form-control">
								<option value="">Select a type</option>
								<form:option value="MONDAY" label="Monday"></form:option>
								<form:option value="TUESDAY" label="Tuesday"></form:option>
								<form:option value="WEDNESDAY" label="Wednesday"></form:option>
								<form:option value="THURSDAY" label="Thursday"></form:option>
								<form:option value="FRIDAY" label="Friday"></form:option>
							</form:select>
							<form:errors path="day" cssClass="error"></form:errors>
						</div>
					</div>
					<div class="form-group row">
						<form:label path="startTime" class="col-sm-2   col-form-label">Start Time</form:label>
						<div class="col-sm-4">
							<form:input placeholder="hh:mm a e.g 12:01 AM"
								class="form-control" path="startTime" />
								<form:errors path="startTime" cssClass="error"></form:errors>
						</div>
					</div>
					<div class="form-group row">
						<form:label path="endTime" class="col-sm-2   col-form-label">End Time</form:label>
						<div class="col-sm-4">
							<form:input placeholder="hh:mm a e.g 12:01 AM"
								class="form-control" path="endTime" />
								<form:errors path="endTime" cssClass="error"></form:errors>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-4 ">
							<button type="submit" class="btn btn-info">Save</button>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>
</body>
</html>