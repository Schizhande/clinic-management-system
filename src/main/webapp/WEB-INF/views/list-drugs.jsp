<%@ include file="commons/navigation.jspf"%>

<div class="container">
	<div class="table-agile-info">
		<h3 style="text-align: center;">Prescribed Drugs</h3>
		<input type="button" value="Add Drug"
			onclick="window.location.href='showDrugForm'; return false;"
			class="btn btn-primary" />
		<hr class="btn-primary">


		<div class="table-responsive text-nowrap ">
			<table id="myTable" class="table table-striped  table-bordered ">
				<thead>
					<tr>
						<th class="th-sm">Drug Id</th>
						<th class="th-sm">Drug Name</th>
						<th class="th-sm">Dosage</th>
						<th class="th-sm">Price</th>
						<th class="th-sm">Quantity</th>
						<th class="th-sm">Frequency</th>
						<th class="th-sm">Duration</th>
						<th class="th-sm">Action</th>
					</tr>
				</thead>
				<!-- loop over and print our drugs-->
				<c:forEach var="tempDrug" items="${drugs}">

					<!-- construct an "update" link with drugs id -->
					<c:url var="updateLink" value="/updateDrug">
						<c:param name="drugId" value="${tempDrug.drugID}" />
					</c:url>



					<tr>
						<td>${tempDrug.drugID }</td>
						<td>${tempDrug.name}</td>
						<td>${tempDrug.dosage}</td>
						<td>${tempDrug.price }</td>
						<td>${tempDrug.quantity }</td>
						<td>${tempDrug.frequency }</td>
						<td>${tempDrug.duration }</td>
						<td><a href="${updateLink }" class="btn btn-info btn-xs" >Edit</a></td>
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