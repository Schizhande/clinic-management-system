<%@ include file="commons/navigation.jspf"%>

<div class="container ">
	<div class="table-agile-info">
		<h3 style="text-align: center;">Staff list</h3>
		<div id="section">

			<input type="button" value="Add Staff"
				onclick="window.location.href='staffForm'; return false;"
				class="btn btn-primary" />
			<hr class="btn-primary">
			<div class="table-responsive text-nowrap ">
				<table id="myTable" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Gender</th>
							<th>Joined Date</th>
							<th>Marital Status</th>
							<th>Job Title</th>
							<th>Action</th>

						</tr>
					</thead>
					<!-- loop over and print our staffs -->
					<c:forEach var="tempStaff" items="${staffs}">

						<!-- construct an "update" link with staff id -->
						<c:url var="updateLink" value="/showFormForStaffUpdate">
							<c:param name="staffId" value="${tempStaff.staffID}" />
						</c:url>

						<!-- construct an "delete" link with staff id -->
						<c:url var="deleteLink" value="/deleteStaff">
							<c:param name="staffId" value="${tempStaff.staffID}" />
						</c:url>


						<!-- construct an "delete" link with staff id -->
						<c:url var="viewLink" value="/viewStaff">
							<c:param name="staffId" value="${tempStaff.staffID}" />
						</c:url>

						<tr>
							<td>${tempStaff.firstName}</td>
							<td>${tempStaff.lastName}</td>
							<td>${tempStaff.email}</td>
							<td>${tempStaff.gender.genderType }</td>
							<td>${tempStaff.joinedDate }</td>
							<td>${tempStaff.maritalStatus.marital }</td>
							<td>${tempStaff.jobTitle.title }</td>

							<td>
								<!-- display the update link --> <a href="${updateLink}"
								class="btn btn-primary btn-xs">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this staff?'))) return false"
								class="btn btn-danger btn-xs">Delete</a>|<a href="${viewLink}"
								class="btn btn-info btn-xs">View</a>
							</td>

						</tr>

					</c:forEach>

				</table>
			</div>
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









