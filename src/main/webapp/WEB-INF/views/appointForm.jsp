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

			<form action="appointmentForm" method="get" class="col-md-12">
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Doctor ID</label>
					<div class="col-sm-4">
						<input type=number id="inputBorder" class="form-control"
							name="doctorId" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Schedule ID</label>
					<div class="col-sm-4">
						<input type=number id="inputBorder" class="form-control"
							name="scheduleId" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-8">
						<button type="submit" class="btn btn-info">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>