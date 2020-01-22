
<%@ include file="commons/navigation.jspf"%>

<%@ include file="commons/footer.jspf"%>

<div id="registrationbox" style="margin-top: 70px;margin-left: 300px"
	class="mainbox col-md-4 ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Reset password</div>
		</div>
		<br> <br>
		<div class="container">
			<form:form action="resetPassword" method="post" class="col-md-8"
				modelAttribute="user">
				<form:hidden path="userID"/>
				<div class="form-group row">
					<form:label path="username" class="col-sm-2 col-form-label">Username(*)</form:label>
					<div class="col-sm-3">
						<form:input class="form-control input-sm" placeholder="username"
							path="username" />
						<form:errors path="username" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="oldPassword" class="col-sm-2 col-form-label">Old Username(*)</form:label>
					<div class="col-sm-3">
						<form:password class="form-control input-sm" placeholder="username"
							path="oldPassword" />
						<form:errors path="oldPassword" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="password" class="col-sm-2 col-form-label">New Password(*)</form:label>
					<div class="col-sm-3">
						<form:password class="form-control input-sm"
							placeholder="password" path="password" id="pass" />
						<form:errors path="password" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">


					<label class="col-sm-2 col-form-label">Confirm Password(*)</label>
					<div class="col-sm-3">
						<input type="password" class="form-control input-sm"
							placeholder="Confirm password" id="confirmpass" />
					</div>
				</div>
				
				<div class="form-group row">
					<div class="col-sm-8">
						<button onclick="return Validate()" type="submit"
							class="btn btn-info">Reset</button>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>
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