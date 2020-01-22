
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div id="content">
	<c:if test="${noDrug}">
			<div class="alert alert-warning">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Hello!</strong> No stock for prescribed drugs
			</div>
		</c:if>
		<h3 style="text-align: center; margin-left: 100px;"><i>Verification
			of Drug stock</i></h3>
			
		<hr class="btn-primary">
		<div class="row">
			<div class="col-sm-6">
				<h3><i>Drugs in Stock</i></h3>
				<hr class="btn-primary">
				<div class="table-responsive">
					<table id="myTable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th class="th-sm">Drug Id</th>
								<th class="th-sm">Drug Name</th>
								<th class="th-sm">Dosage</th>
								<th class="th-sm">Price</th>
								<th class="th-sm">Quantity</th>
								<th class="th-sm">Frequency</th>
								<th class="th-sm">Duration</th>
							</tr>
						</thead>
						<!-- loop over and print our drugs-->
						<c:forEach var="drug" items="${drugInStock}">

							<tr>
								<td>${drug.drugID }</td>
								<td>${drug.name}</td>
								<td>${drug.dosage}</td>
								<td>${drug.price}</td>
								<td>${drug.quantity}</td>
								<td>${drug.frequency }</td>
								<td>${drug.duration }</td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="3"><strong>Total Price</strong></td>
							<td colspan="2">$ ${totalPrice }</td>
							<td colspan="2"><a class="btn btn-info btn-xs"
								href="<c:url value='/purchaseMedicine?recordId=${recordId}&totalAmount=${totalPrice }'/>">Purchase</a></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-sm-6">
				<h3><i>Drugs not in stock</i></h3>
				<hr class="btn-primary">
				<div class="table-responsive ">
					<table id="myTable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th class="th-sm">Drug Id</th>
								<th class="th-sm">Drug Name</th>
								<th class="th-sm">Dosage</th>
								<th class="th-sm">Price</th>
								<th class="th-sm">Quantity</th>
								<th class="th-sm">Frequency</th>
								<th class="th-sm">Duration</th>
							</tr>
						</thead>
						<c:if test="${empty drugNotInStock}">
							<tr class="alert alert-info">
								<td colspan="7">All prescribed drugs are in the stock</td>
							</tr>
						</c:if>
						<!-- loop over and print our drugs-->
						<c:forEach var="drug" items="${drugNotInStock}">

							<tr>
								<td>${drug.drugID }</td>
								<td>${drug.name}</td>
								<td>${drug.dosage}</td>
								<td>${drug.price}</td>
								<td>${drug.quantity}</td>
								<td>${drug.frequency }</td>
								<td>${drug.duration }</td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="7"><a class="btn btn-info btn-xs"
								href="<c:url value='/printPrecriptionPdf?recordId=${recordId}'/>">Print
									Pdf</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="commons/footer.jspf"%>
</body>
</html>