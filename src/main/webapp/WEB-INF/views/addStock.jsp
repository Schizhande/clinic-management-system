
<%@ include file="commons/navigation.jspf"%>
<br>
<div id="registrationbox" class="mainbox col-md-6   col-md-offset-2">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Add Drug Stock</div>
		</div>
		<br> <br>
		<div class="container">
			<form:form action="saveStock" modelAttribute="stock" method="POST"
				class="col-md-8">

				<form:hidden path="stockID" />
				<div class=" form-group row">
					<form:label class="col-sm-2  col-form-label" path="drug.drugID">Drug Name</form:label>
					<div class="col-sm-8">
						<form:select id="inputBorder" class="form-control"
							path="drug.drugID">
							<option value="nn">Select a drug</option>
							<c:forEach items="${drugs}" var="drug">
								<option value="${drug.drugID}">${drug.name}</option>
							</c:forEach>
						</form:select>
						<form:errors path="drug.drugID" cssClass="error"></form:errors>
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
					<form:label path="expiryDate" class="col-sm-2 col-form-label">Expiry Date</form:label>
					<div class="col-sm-8">
						<form:input type="date" class="form-control" path="expiryDate" />
						<form:errors path="expiryDate" cssClass="error"></form:errors>
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