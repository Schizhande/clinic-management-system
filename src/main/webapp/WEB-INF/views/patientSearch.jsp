<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<br>
<br>
<div style="margin-left: 250px; width: 70%;">
	<c:if test="${failAdd}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong> Hello!</strong> There is pending visit for this patient 
		</div>
		<hr class="btn-primary">
	</c:if>

</div>
<div id="registrationbox" style="margin-left: 180px; margin-top:50px;"
	class="mainbox col-md-6 ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Search patient</div>
		</div>
		<br> <br>
		<div class="container">
			<form action="searchPatient" name="treatForm" method="get" onsubmit="return validate()" class="col-md-8">
				<div class="form-group row">
					<label class="col-sm-2   col-form-label">Patient ID</label>
					<div class="col-sm-6">
						<input class="form-control" name="patientID" />
					</div>

					<div class="col-sm-4  ">
						<button type="submit" class="btn btn-info">Search</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>
<script type="text/javascript">
	function validate() {
		var name = document.forms["treatForm"]["patientID"];
		if (name.value == "") {
			window.alert("Please enter patientID");
			name.focus();
			return false;
		}

	}
</script>
<%@ include file="commons/footer.jspf"%>
</body>
</html>