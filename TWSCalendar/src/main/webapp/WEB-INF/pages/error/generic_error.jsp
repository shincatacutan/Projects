<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>PeopleSoft TWS Calendar</title>
<link rel="shortcut icon"
	href="<c:url value="/resources/favicon_oh.ico" />">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/jquery-ui.css" />"
	rel="stylesheet">

<%-- 
	    <link href="<c:url value="/resources/css/jquery-ui.structre.css" />" rel="stylesheet"> --%>

<script src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>
<script src="<c:url value="/resources/js/angular.js"/>"></script>
<%-- <script src="<c:url value="/resources/js/angular-route.js"/>"></script> --%>


</head>

<body>
	<div id="body_container">
		<header>header</header>

		<div id="body_dv">
			<div class="error_dv">
				Error Code:<br /> <span>${errCode}</span><br />
				<br /> Error Message:<br /> <span>${errMsg}</span>
			</div>
		</div>

		<footer>footer</footer>
	</div>
</body>
</html>
