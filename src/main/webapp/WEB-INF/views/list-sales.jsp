<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center; margin-left: 200px;">sale list</h3>


		<hr class="btn-primary">

		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Sale Id</th>
						<th class="th-sm">Date</th>
						<th class="th-sm">Drug Name(s)</th>
						<th class="th-sm">Drug Price</th>
						<th class="th-sm">Payment Method</th>

					</tr>
				</thead>
				<!-- loop over and print our sales-->
				<c:forEach var="tempsale" items="${sales}">
					<tr>

						<td>${tempsale.salesID}</td>
						<td>${tempsale.issedDate}</td>
						<td><c:forEach var="tempDrug" items="${tempsale.drugs}">
								<strong>Drug name:</strong>${tempDrug.name } &ensp; &ensp; 
							<strong>Price:</strong> ${tempDrug.price} &ensp; &ensp; 
							<strong>Drug quantity:</strong>${tempDrug.price }<br>
							</c:forEach></td>
						<td>${tempsale.payment.amount }</td>
						<td>${tempsale.payment.paymentMethod.method }</td>


					</tr>

				</c:forEach>

			</table>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>
<script>
	$(document).ready(function() {
		$("#myTable").DataTable({
			"lengthMenu" : [ [ 5, 10, 15, 20, -1 ], [ 5, 10, 15, 20, "All" ] ],
			"ordering" : false,
			stateSave : true
		});
	});
</script>
</body>
</html>