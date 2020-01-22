<%@ include file="commons/navigation.jspf"%>
<div id="registrationbox" class="mainbox col-md-8   col-sm-6  ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Register Or Update Patient Details</div>
		</div>
		<br> <br>
		<div class="container">
			<form:form action="saveOrUpdatePatient" modelAttribute="patient"
				method="POST" class="col-md-10">

				<form:hidden path="patientID" />

				<div class="form-group row">
					<form:label path="firstName" class="col-sm-2 col-form-label">First
						Name(*)</form:label>
					<div class="col-sm-3">
						<form:input class="form-control input-sm" placeholder="First name"
							path="firstName" />
						<form:errors path="firstName" cssClass="error"></form:errors>
					</div>

					<form:label path="lastName" class="col-sm-2 col-form-label">Last
						Name(*)</form:label>
					<div class="col-sm-3">
						<form:input class="form-control input-sm" path="lastName"
							placeholder="Last Name" />
						<form:errors path="lastName" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="email" class="col-sm-2 col-form-label">Email(*)</form:label>
					<div class="col-sm-3">
						<form:input type="email" class="form-control input-sm"
							placeholder="Email" path="email" />
						<form:errors path="email" cssClass="error"></form:errors>
					</div>

					<form:label path="mobile" class="col-sm-2 col-form-label">Mobile(*)</form:label>
					<div class="col-sm-3">
						<form:input class="form-control input-sm"
							placeholder="Enter phone number...(+263...)" path="mobile" />
						<form:errors path="mobile" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="form-group row">
					<form:label path="nationalID" class="col-sm-2 col-form-label">National ID(*)</form:label>
					<div class="col-sm-3">
						<form:input class="form-control input-sm"
							placeholder="Enter national ID ....(e.g 07-122398H07)" path="nationalID" />
						<form:errors path="nationalID" cssClass="error"></form:errors>
					</div>

					<form:label path="dob" class="col-sm-2 col-form-label">Date of
						Birth(*)</form:label>
					<div class="col-sm-3">
						<form:input path="dob" type="date" class="form-control input-sm" />
						<form:errors path="dob" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="form-group row">
					<form:label class="col-form-label col-sm-2" path="gender.id">Gender(*)</form:label>
					<div class="col-sm-3">
						<form:select id="inputBorder" class="form-control input-sm"
							path="gender.id">
							<option value="-1">Select gender</option>
							<c:forEach items="${genders}" var="g">
								<option value="${g.id}">${g.genderType}</option>
							</c:forEach>
						</form:select>
						<form:errors path="gender.id" cssClass="error"></form:errors>
					</div>

					<form:label class="col-form-label col-sm-2" path="maritalStatus.id">Marital Status(*)</form:label>
					<div class="col-sm-3">
						<form:select id="inputBorder" class="form-control input-sm"
							path="maritalStatus.id">
							<option value="-1">Select MaritalStatus</option>
							<c:forEach items="${maritals}" var="marital">
								<option value="${marital.id}">${marital.marital}</option>
							</c:forEach>
						</form:select>
						<form:errors path="maritalStatus.id" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="address" class="col-sm-2 col-form-label">Address(*)</form:label>
					<div class="col-sm-3">
						<form:textarea id="inputBorder" class="form-control input-sm"
							path="address" />
						<form:errors path="address" cssClass="error"></form:errors>
					</div>
					<form:label path="joinedDate" class="col-sm-2 col-form-label">Joined
							Date(*)</form:label>
					<div class="col-sm-3">
						<form:input path="joinedDate" type="date"
							class="form-control input-sm" />
						<form:errors path="joinedDate" cssClass="error"></form:errors>
					</div>
				</div>
				<c:if test="${!edit}">

					<div class="form-group row">
						<form:label path="user.username" class="col-sm-2 col-form-label">Username(*)</form:label>
						<div class="col-sm-3">
							<form:input class="form-control input-sm" placeholder="username"
								path="user.username" />
							<form:errors path="user.username" cssClass="error"></form:errors>
						</div>

						<form:label path="user.password" class="col-sm-2 col-form-label">Password(*)</form:label>
						<div class="col-sm-3">
							<form:password class="form-control input-sm"
								placeholder="password" path="user.password" id="pass" />
							<form:errors path="user.password" cssClass="error"></form:errors>
						</div>
					</div>
					<div class="form-group row">


						<label class="col-sm-2 col-form-label">Confirm Password(*)</label>
						<div class="col-sm-3">
							<input type="password" class="form-control input-sm"
								placeholder="Confirm password" id="confirmpass" />
						</div>
					</div>

				</c:if>
				<div class="form-group row">
					<div class="col-sm-8">
						<button onclick="return Validate()" type="submit"
							class="btn btn-info">Save</button>
					</div>
				</div>

			</form:form>
		</div>

	</div>
</div>
<%@ include file="commons/footer.jspf"%>
<!-- Validating Password -->
<script type="text/javascript">
	function Validate() {
		var password = document.getElementById("pass").value;
		var confirmpass = document.getElementById("confirmpass").value;
		if (password != confirmpass) {
			alert("Password does Not Match.");
			return false;
		}
		return true;
	}
</script>
</body>
</html>
