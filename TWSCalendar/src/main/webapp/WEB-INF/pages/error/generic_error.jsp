<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TWS Calendar Generator</title>

<link rel="shortcut icon"
	href="<c:url value="/resources/favicon_oh.ico" />">


<link href="<c:url value="/resources/css/jquery-ui.min.css" />"
	rel="stylesheet" />
<link
	href="<c:url value="/resources/css/jquery-ui.structure.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/jquery-ui.theme.min.css" />"
	rel="stylesheet" />
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/bootstrap-table.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/bootstrap-theme.css" />"
	rel="stylesheet" />

<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<script src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-validate-min.js" />"></script>
<script src="<c:url value="/resources/js/additional-methods.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-table.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

</head>

<body>

	<div id="main-box">
	<header>
			<img alt="Optum Logo"
				src="<c:url value="/resources/images/optumlogo.png" />"> <span>
				TWS Calendar Generator</span>
		</header>
		
		<div id="content-box">
			<div class="error_dv">
			Error Code:<br/> <span>${errCode}</span><br/><br/>
			Error Message:<br/> <span>${errMsg}</span></div>
		</div>
		<footer>
			TWS Calendar Generator | &copy;<span>Optum</span>, Inc. All rights
			reserved.
		</footer>
	</div>
</body>