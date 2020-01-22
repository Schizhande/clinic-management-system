<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center; ">Stock list</h3>
		<div id="section">
			<input type="button" value="Add New Stock"
				onclick="window.location.href='addStock'; return false;"
				class="btn btn-primary" />


			<hr class="btn-primary">

			<div class="table-responsive text-nowrap ">
				<table id="myTable" class="table table-striped  table-bordered ">
					<thead>
						<tr>
							<th class="th-sm">Stock ID</th>
							<th class="th-sm">Drug Name</th>
							<th class="th-sm">Quantity</th>
							<th class="th-sm">Expiry Date</th>
							<th class="th-sm">Action</th>

						</tr>
					</thead>
					<!-- loop over and print our stocks -->
					<c:forEach var="stock" items="${stocks}">

						<!-- construct an "update" link with stock id -->
						<c:url var="updateLink" value="/showFormForStockUpdate">
							<c:param name="stockId" value="${stock.stockID}" />
						</c:url>

						<!-- construct an "delete" link with stock id -->
						<c:url var="deleteLink" value="/deleteStock">
							<c:param name="stockId" value="${stock.stockID}" />
						</c:url>

						<tr>
							<td>${stock.stockID }</td>
							<td>${stock.drug.name}</td>
							<td>${stock.quantity}</td>
							<td>${stock.expiryDate }</td>

							<td>
								<!-- display the update link --> <a href="${updateLink}"
								class="btn btn-primary btn-xs">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this stock?'))) return false"
								class="btn btn-danger btn-xs">Delete</a>
							</td>

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









