
<%@ include file="commons/navigation.jspf"%>
<br>
<div class="container">
	<div class="  col-md-offset-2 col-md-8">
		<h3 style="text-align:center">Patient feedbacks</h3>
		<hr class="btn-info">
		<c:forEach var="feedback" items="${feedbacks }">

			<div class="row" style="color:blue;">
				<h4> <span class="glyphicon glyphicon-user"></span> ${feedback.patient.firstName } ${feedback.patient.lastName }</h4>
			</div>
			
			<div>
			<p class="msg_cotainer"><span class="glyphicon glyphicon-envelope"></span> ${feedback.description }</p>
			</div>
		</c:forEach>
	</div>
</div>
<%@ include file="commons/footer.jspf"%>

</body>
</html>