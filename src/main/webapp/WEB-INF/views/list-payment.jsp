<%@ include file="commons/navigation.jspf"%>
<div class="container ">
	<div class="table-agile-info">
		<h3 style="text-align: center;">Payments</h3>
		<div id="section">


			<hr class="btn-primary">
			<div class="table-responsive text-nowrap ">
				<table id="myTable" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Payment ID</th>
							<th>Payment Date</th>
							<th>Amount</th>
							<th>Payment Method</th>
							<th>Payment Purpose</th>
						</tr>
					</thead>
					<!-- loop over and print our payments -->
					<c:forEach var="temppayment" items="${payments}">

						<tr>
							<td>${temppayment.paymentID}</td>
							<td>${temppayment.paymentDate}</td>

							<td>${temppayment.amount }</td>
							<td>${temppayment.paymentMethod.method}</td>
							<td>${temppayment.paymentPurpose.purpose }</td>
						</tr>

					</c:forEach>

				</table>
			</div>
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









