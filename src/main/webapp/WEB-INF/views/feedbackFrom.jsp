<%@ include file="commons/navigation.jspf"%>
<br>

<c:choose>

	<c:when test="${feedbackSuccess}">
		<div class="container" style="margin-left: 400px; width: 70%;">
			<div class="content">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong><i class="material-icons">check_circle </i> Hello!
						<security:authentication property="principal.username" /></strong> Post
					successful send
				</div>
				<hr class="btn-primary">
				<div style="margin-left: 40px; width: 40%;">
					<h2>
						<strong>Your post</strong>
					</h2>
					<hr class="btn-primary">
					<p>${feedback.description }</p>
					<p>
						<a href="deletePost?feedbackId=${feedback.id }"
							class="btn btn-link">Cancel your post</a>
					</p>
				</div>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<div class="container col-md-6   col-md-offset-2">

			<c:if test="${deleteSuccess}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Hello! <security:authentication
							property="principal.username" /></strong> Post deleted successful
				</div>
			</c:if>
		</div>
		<div id="registrationbox" class="mainbox col-md-6   col-md-offset-2">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">You are free to express your views
						to us</div>
				</div>
				<br> <br>
				<div class="container">
					<form:form action="saveFeedBack" modelAttribute="feedback"
						method="POST" class="col-md-10">

						<form:hidden path="patient.patientID" />
						<form:hidden path="id" />
						<div class="form-group row">
							<form:label path="description" class="col-sm-2 col-form-label">Describe for view
						</form:label>
							<div class="col-sm-6">
								<form:textarea id="inputBorder" class="form-control"
									path="description" />
								<form:errors path="description" cssClass="error"></form:errors>
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
	</c:otherwise>
</c:choose>

<%@ include file="commons/footer.jspf"%>
</body>
</html>