
<%@ include file="commons/navigation.jspf"%>
<br>
<c:choose>
	<c:when test="${record==null }">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Hello! </strong> No details for this record
		</div>

	</c:when>
	<c:otherwise>
		<div class="container">

			<div class="table-agile-info">
				<h2 style="text-align: center">Patient's details for the visit</h2>
				<div class="table-responsive text-nowrap ">
					<table id="myTable" class="table  table-bordered ">
						<thead>
							<tr>
								<th>Blood pressure</th>
								<th>Temperature</th>
								<th>Weight</th>
								<th>Symptoms</th>
								<th>Disease</th>
								<th>Doctor comment</th>
								<th>Action</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td>${record.basictest.bp }</td>
								<td>${record.basictest.temperature}</td>
								<td>${record.basictest.weight }</td>

								<td><c:forEach var="symptoms"
										items="${record.getRecordSymptom()}">
								${symptoms.symptom.symptomName} <br>
									</c:forEach></td>
								<td><c:forEach var="diseases"
										items="${record.getRecordDisease()}">
								${diseases.getDisease().getDiseaseName()} <br>
									</c:forEach></td>

								<td>${record.comment.description }</td>
								<!-- construct an "update" link with staff id -->
								<c:url var="commentLink" value="/addComment">
									<c:param name="recordId" value="${record.recordID}" />
								</c:url>

								<!-- construct an "delete" link with staff id -->
								<c:url var="prescribeLink" value="/prescribe">
									<c:param name="recordId" value="${record.recordID}" />
								</c:url>
								<td>
									<!-- display the update link --> <a href="${prescribeLink}"
									class="btn btn-primary btn-xs">Prescribe</a> <c:if
										test="${record.comment.description==null }"> |<a
											href="${commentLink}" class="btn btn-primary btn-xs">Add
											comment</a>
									</c:if>
								</td>
							</tr>
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</c:otherwise>

</c:choose>

<%@ include file="commons/footer.jspf"%>
</body>
</html>