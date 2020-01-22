
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>

<div id="registrationbox" style="margin-top: 30px; margin-left: 70px"
	class="mainbox col-md-9  ">
	<c:if test="${alreadyAdded}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello!</strong> Entered drug is already added
		</div>
	</c:if>
	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Prescribe medicines</div>
		</div>
		<form:form>

		</form:form>
		<br> <br>
		<div class="container">
			<form action="prescribePatient" name="preForm" method="get" onsubmit="return validate()" class="col-md-12">
				<div class="form-group row">
					<label class="col-sm-2   col-form-label">Prescribe drug
						name</label>
					<div class="col-sm-4">
						<input class="form-control"  id="w-input-search" name="drugName" />
					</div>
					<input name="recordId" value="${record.recordID}" hidden=true>
					<div class="col-sm-2  ">
						<button type="submit" class="btn btn-info">Add</button>
					</div>
				</div>
			</form>
		</div>

		<div class="container">
			<div class="col-sm-10">
				<h3>Prescribed Drug List</h3>
				<hr class="btn-primary">
				<div class="table-responsive text-nowrap ">
					<table id="myTable" class="table table-stripped">
						<thead>
							<tr>
								<th class="th-sm">Drug Id</th>
								<th class="th-sm">Drug Name</th>
								<th class="th-sm">Dosage</th>
								<th class="th-sm">Quantity</th>
								<th class="th-sm">Frequency</th>
								<th class="th-sm">Duration</th>
								<th class="th-sm">Action</th>


							</tr>
						</thead>
						<!-- loop over and print our drugs-->
						<c:forEach var="tempDrug" items="${drugs}">

							<!-- construct an "delete" link with drugs id -->
							<c:url var="removeLink" value="/removeDrug">
								<c:param name="drugId" value="${tempDrug.drug.drugID}" />
								<c:param name="recordId" value="${record.recordID}" />
							</c:url>

							<tr>
								<td>${tempDrug.drug.drugID }</td>
								<td>${tempDrug.drug.name}</td>
								<td>${tempDrug.drug.dosage}</td>
								<td>${tempDrug.drug.quantity}</td>
								<td>${tempDrug.drug.frequency }</td>
								<td>${tempDrug.drug.duration }</td>

								<td><a href="${removeLink}" class="btn btn-danger btn-xs">Remove</a></td>

							</tr>

						</c:forEach>

					</table>
				</div>
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
 
	function validate() {
		var name = document.forms["preForm"]["drugName"];
		if (name.value == "") {
			window.alert("Please enter patientID");
			name.focus();
			return false;
		}

	}
	
	  $(document).ready(function() {

		$('#w-input-search').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getTags',
			paramName: "tagName",
			delimiter: ",",
		   transformResult: function(response) {
			    	
			return {      	
			  //must convert json to javascript object before process
			  suggestions: $.map($.parseJSON(response), function(item) {
			            	
			      return { value: item.tagName, data: item.id };
			   })
			            
			 };
			        
	            }
			    
		 });
					
	  });
	 
</script>
</body>
</html>