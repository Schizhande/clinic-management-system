<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center; ">Prescribed Drugs</h3>
		<hr class="btn-primary">

		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Drug Id</th>
						<th class="th-sm">Drug Name</th>
						<th class="th-sm">Dosage</th>
						<th class="th-sm">Quantity</th>
						<th class="th-sm">Frequency</th>
						<th class="th-sm">Duration</th>

					</tr>
				</thead>
				<!-- loop over and print our drugs-->
				<c:forEach var="tempDrug" items="${pres}">

					<!-- construct an "update" link with drugs id -->
					<c:url var="updateLink" value="/updateDrug">
						<c:param name="drugId" value="${tempDrug.drugID}" />
					</c:url>



					<tr>
						<td>${tempDrug.drug.drugID }</td>
						<td>${tempDrug.drug.name}</td>
						<td>${tempDrug.drug.dosage}</td>
						<td>${tempDrug.drug.price }</td>
						<td>${tempDrug.drug.quantity }</td>
						<td>${tempDrug.drug.frequency }</td>
						<td>${tempDrug.drug.duration }</td>

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