<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<div id="registrationbox" style="margin: 100px"
	class="mainbox col-md-7  ">

	<div class="panel panel-info">

		<div class="panel-heading">
			<div class="panel-title">Doctor comment</div>
		</div>
		<br> <br>
		<div class="container">
			<form:form action="saveComment" modelAttribute="comment"
				method="POST" class="col-md-12">

				<form:hidden path="record.recordID" />


				<div class="form-group row">
					<form:label path="description" class="col-sm-2 col-form-label">Doctor Comment</form:label>
					<div class="col-sm-5">
						<form:textarea id="inputBorder" class="form-control"
							path="description" />
					</div>
				</div>
				<div class="form-group row">
					<div class=" col-sm-5 col-sm-offset-2">
						<button type="submit" class="form-control btn btn-info">Save</button>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>

</body>
</html>