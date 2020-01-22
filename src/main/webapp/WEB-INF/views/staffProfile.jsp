<%@ include file="commons/navigation.jspf"%>

<div  style="margin-top: 50px;" class="mainbox col-md-8   ">
	<c:if test="${resetSuccess}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello!</strong> Reset password success
		</div>
	</c:if>
	<c:if test="${saveSuccess}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello!</strong> Staff details successfully saved or updated
		</div>
	</c:if>
	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">
				<strong>Staff Profile</strong>
			</div>
			<strong>StaffID:</strong> ${staff.staffID }
		</div>

		<table class=" table table-stripped">
			<tr>
				<th>First Name</th>
				<td>${staff.firstName}</td>


				<th class="col-md-3">National ID</th>
				<td class="col-md-3">${staff.nationalID }</td>
			</tr>
			<tr>
				<th>Surname</th>
				<td>${staff.lastName}</td>

				<th>Date Of Birth</th>
				<td>${staff.dob }</td>
			</tr>

			<tr>
				<th>Gender</th>
				<td>${staff.gender.genderType}</td>

				<th>Joined Date</th>
				<td>${staff.joinedDate }</td>
			</tr>

			<tr>
				<th>Mobile Number</th>
				<td>${staff.mobile}</td>

				<th>Address</th>
				<td>${staff.address}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${staff.email}</td>

				<th>Marital Status</th>
				<td>${staff.maritalStatus.marital}</td>
			</tr>
			<tr>
				<th>Job Title</th>
				<td>${staff.jobTitle.title }</td>
				<th>Specialty</th>
				<td>${staff.specialty.name }
			</tr>
			<tr>
				<th>Username</th>
				<td>${staff.user.username}</td>

				<th>Account Status</th>
				<td>${staff.user.state}</td>
			</tr>

		</table>
		<br>

	</div>
</div>
<%@ include file="commons/footer.jspf"%>
</body>
</html>