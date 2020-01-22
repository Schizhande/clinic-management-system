
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<br>
<div id="registrationbox" class="mainbox col-md-6 col-md-offset-1 ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Add Drug</div>
		</div>
		<br> <br>
		<div class="container">
			<c:choose>
				<c:when test="${prescription}">
					<c:url var="myUrl" value="saveDrugPlusPrescript"></c:url>
				</c:when>
				<c:otherwise>
					<c:url var="myUrl" value="saveDrug"></c:url>
				</c:otherwise>
			</c:choose>

			<form:form action="${myUrl}" modelAttribute="drug" method="POST"
				class="col-md-8">

				<form:hidden path="drugID" />
				<form:hidden path="recordId"/>

				<div class="form-group row">
					<form:label path="name" class="col-sm-2 col-form-label">Drug Name</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="name" />
						<form:errors path="name" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="dosage" class="col-sm-2 col-form-label">Drug Dosage</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="dosage" />
						<form:errors path="dosage" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="quantity" class="col-sm-2 col-form-label">Drug Quantity</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="quantity" />
						<form:errors path="quantity" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="price" class="col-sm-2 col-form-label">Drug Price</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="price" />
						<form:errors path="price" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="frequency" class="col-sm-2 col-form-label">Frequency</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="frequency" />
						<form:errors path="frequency" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="duration" class="col-sm-2 col-form-label">Duration</form:label>
					<div class="col-sm-8">
						<form:input class="form-control" path="duration" />
						<form:errors path="duration" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-8">
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