<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
 
</head>
<body>
	<div class="success">
		<h1>Greeting : ${greeting} This is a welcome page.</h1>
		<p>
			<a href="<c:url value='/patientForm'/> ">ADD new patient</a>
		</p>
		<p>
			<a href="<c:url value='/listPatients'/> ">list patients</a>
		</p>
		<p>
			<a href="<c:url value='/logout' />">CLICK</a>
		</p>
	</div>
</body>
</html>