<%@ include file="commons/navigation.jspf"%>

<div id="registrationbox" style="margin: 100px"
	class="mainbox col-md-6  col-sm-6  ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Make payment</div>
		</div>
		<br> <br>
		<div class="container">
			<c:choose>
				<c:when test="${payMedicine }">
					<c:url var="myUrl" value="/payMedicineBill"></c:url>
				</c:when>
				
				<c:otherwise>
				<c:url var="myUrl" value="/savePayment"></c:url>
				</c:otherwise>
			</c:choose>
			 
			<form:form action="${myUrl}" modelAttribute="payment" method="POST"
				class="col-md-12">

				<form:hidden path="paymentID" />
				<form:hidden path="record.recordID" />
				<div class="form-group row">
					<form:label path="amount" class="col-sm-2 col-form-label">Amount(*)
						 </form:label>
					<div class="col-sm-3">
						<form:input class="form-control" placeholder="enter amount"
							path="amount" />
						<form:errors path="amount" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label class="col-form-label col-sm-2"
						path="paymentPurpose.id">Payment Purpose(*)</form:label>
					<div class="col-sm-3">
						<form:select id="inputBorder" class="form-control input-sm"
							path="paymentPurpose.id">
							<option value="aa">Select purpose</option>
							<c:forEach items="${purposes}" var="purpose">
								<option value="${purpose.id}">${purpose.purpose}</option>
							</c:forEach>
						</form:select>
						<form:errors path="paymentPurpose.id" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="form-group row">
					<form:label path="paymentMethod.id" class="col-sm-2 col-form-label">Payment method(*)</form:label>
					<div class="col-sm-3">
						<form:select id="inputBorder" class="form-control input-sm"
							path="paymentMethod.id">
							<option value="aa">Select method</option>
							<c:forEach items="${methods}" var="method">
								<option value="${method.id}">${method.method}</option>
							</c:forEach>
						</form:select>
						<form:errors path="paymentMethod.id" cssClass="error"></form:errors>
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