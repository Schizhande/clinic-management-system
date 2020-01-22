<%@ include file="commons/navigation.jspf"%>
 <div class="container">
<h2 style="text-align: center;"> Treatment session</h2>
<hr class="btn-primary">
<div  class=" col-md-offset-3 scr content">
<br>

		<c:forEach var="qn" items="${visited}">
			<div class="msg_cotainer">${qn }</div>
			<c:forEach var="chart" items="${charts}">
			<c:if test="${chart.getResponse(qn)!=null}">
				<p class="msg_cotainer_send">${chart.getResponse(qn)}</p>
				</c:if>
			</c:forEach>
		</c:forEach>
<c:if test="${findD }">
<a href="/showPatientRecord?recordId='${recordId }'">View full record details </a>

</c:if>
		<c:if test="${!findD }">

			<form method="get" action="treatPatient" name="treatForm"
				onsubmit="return validate()">
				<div class="form-group row">
					<input name="recordId" value="${recordId }" hidden=true />  
					<div style="width:500px; margin-left: 20px;">
						<div class="input-group m-bot15">
                                <input  style="border-top: 0px;"name="answer" class="form-control" placeholder="type symptom here...."/>
                                              <span class="input-group-btn">
                                                <i class="fa fa-paper-plane" aria-hidden="true"></i>
                                                <button  type="submit" value="save"  class="btn  btn-info" > <i style="font-size:20px" class="fa fa-paper-plane"></i></button>
                                              </span>
                            </div>

					</div>
				</div>
			</form>
		</c:if>
	</div>
	</div>
<%@ include file="commons/footer.jspf"%>

<script type="text/javascript">
	function validate() {
		var name = document.forms["treatForm"]["answer"];
		if (name.value == "") {
			window.alert("Please enter answer");
			name.focus();
			return false;
		}

	}
</script>
</body>
</html>