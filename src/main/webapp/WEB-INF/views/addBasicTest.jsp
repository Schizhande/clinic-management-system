<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<br>
<div id="registrationbox" class="mainbox col-md-6   col-md-offset-2">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Capture basic test details</div>
		</div>
		<br> <br>
		<div class="container">
			<form:form action="saveBasicTest" modelAttribute="basicTest"
				method="POST" class="col-md-10">

				<form:hidden path="basicTestID" />
				<form:hidden path="record.recordID" />
				<div class="form-group row">
					<form:label path="weight" class="col-sm-2 col-form-label">Patient Weight
						</form:label>
					<div class="col-sm-6">
						<form:input class="form-control" path="weight" />
						<form:errors path="weight" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="temperature" class="col-sm-2 col-form-label">Body Temperature
						 </form:label>
					<div class="col-sm-6">
						<form:input class="form-control" path="temperature" />
						<form:errors path="temperature" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="bp" class="col-sm-2 col-form-label">Blood Pressure
						</form:label>
					<div class="col-sm-6">
						<form:input class="form-control" path="bp" />
						<form:errors path="bp" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-6">
						<button type="submit" class="btn btn-info">Save</button>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>
</body>
</html>