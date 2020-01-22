<%@ include file="commons/navigation.jspf"%>

<c:choose>

	<c:when test="${notFound}">
		<br>
		<br>
		<div class="container" style="margin-left: 250px; width: 70%;">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong> Hello!</strong> Patient not found
			</div>
			<hr class="btn-primary">

			<p>
				Enter patient as new patient <a href="patientForm"
					class="btn btn-link">Click here</a>
			</p>
		</div>
	</c:when>

	<c:otherwise>
		<c:if test="${saveSuccess}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Hello!</strong> New patient successfully added or updated
			</div>
		</c:if>
		<div id="loginbox" style="margin-top: 50px; margin-left:100px"
			class="mainbox col-md-8   ">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">
						<strong>Patient Profile</strong>
					</div>
					<strong>PatientID:</strong> ${patient.patientID }
				</div>
					<table class=" table table-stripped">
						<tr>
							<th>First Name</th>
							<td>${patient.firstName}</td>


							<th class="col-md-3">National ID</th>
							<td class="col-md-3">${patient.nationalID }</td>
						</tr>
						<tr>
							<th>Surname</th>
							<td>${patient.lastName}</td>

							<th>Date Of Birth</th>
							<td>${patient.dob }</td>
						</tr>

						<tr>
							<th>Gender</th>
							<td>${patient.gender.genderType}</td>

							<th>Joined Date</th>
							<td>${patient.joinedDate }</td>
						</tr>

						<tr>
							<th>Mobile Number</th>
							<td>${patient.mobile}</td>

							<th>Address</th>
							<td>${patient.address}</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>${patient.email}</td>

							<th>Marital Status</th>
							<td>${patient.maritalStatus.marital}</td>
						</tr>
						<security:authorize access="hasRole('PATIENT')">
							<tr>
								<th>Username</th>
								<td>${patient.user.username}</td>

								<th>Account Status</th>
								<td>${patient.user.state}</td>
							</tr>
						</security:authorize>
					</table>
				</div>
				<br>
				<security:authorize access="hasRole('RECEPTIONIST')">
					<a class="btn btn-link"
						href="<c:url value='/addToWaitingList?patientId=${patient.patientID}'/>">Add
						to weighting list</a>
				</security:authorize>

			</div>

	</c:otherwise>
</c:choose>

<%@ include file="commons/footer.jspf"%>
</body>
</html>