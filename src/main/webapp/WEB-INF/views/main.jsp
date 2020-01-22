<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<br>
<security:authorize access="hasRole('ADMIN')">
	<h3>Admin Dashboard</h3>

	<hr class="btn-primary">
	<div class="row">
		<div class="col-md-2">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-user  fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class='huge'>${pats }</div>
							<div>Patients</div>
						</div>
					</div>
				</div>
				<a href="listPatients">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class=" col-md-2">
			<div class="panel panel-green">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-comments fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class='huge'>${feedbacks }</div>
							<div>Feedback</div>
						</div>
					</div>
				</div>
				<a href="viewFeedback">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class=" col-md-2">
			<div class="panel panel-yellow">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-user fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class='huge'>${appointments }</div>
							<div>Appointment</div>
						</div>
					</div>
				</div>
				<a href="listAppointment">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class=" col-md-2">
			<div class="panel panel-red">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-user  fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class='huge'>${staffs }</div>
							<div>Staffs</div>
						</div>
					</div>
				</div>
				<a href="listStaffs">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
				<div id="chartContainer" style="height: 200px; width: 100%;"></div>
			</div>
		</div>
	</div>


</security:authorize>
<div>

	<c:if test="${saveSuccess}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello! <security:authentication
					property="principal.username" /></strong> Reset password success
		</div>
	</c:if>
</div>

<security:authorize access="hasRole('PATIENT')">

	<div id="loginbox" style="margin-top: 10px;"
		class="mainbox col-md-8   ">
<h2 style="text-align:center;">
			Welcome:
			<security:authentication property="principal.username" />
		</h2>
		<hr class="btn-primary">
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
			<br>


		</div>
	</div>
</security:authorize>
<security:authorize
	access="hasRole('NURSE') or hasRole('RECEPTIONIST') or hasRole('PHARMACIST') or hasRole('DOCTOR')">

	<div style="margin-to:10px;" class="mainbox col-md-8   ">
		<h2 style="text-align:center;">
			Welcome:
			<security:authentication property="principal.username" />
		</h2>
		<hr class="btn-primary">
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
</security:authorize>
<div id="footer">Copyright @ clinicmanagement.com</div>
<script type="text/javascript">
	window.onload = function() {

		var dps = [ [] ];
		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled : true,
			theme : "light1",// "dark1", "dark2"
			title : {
				text : "Clinic Data"
			},
			subtitles : [ {
				text : "Based on number of users in the database"
			} ],
			axisY : {
				title : "User numbers (in numbers)",
				suffix : "users",
				includeZero : false
			},
			axisX : {
				title : "Clinic users"
			},
			data : [ {
				type : "column",
				yValueFormatString : "#,##0 users",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chart.render();

	}
</script>
</body>
</html>