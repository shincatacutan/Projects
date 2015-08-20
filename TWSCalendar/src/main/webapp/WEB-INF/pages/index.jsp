<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PeopleSoft TWS Calendar</title>

<link rel="shortcut icon"
	href="<c:url value="/resources/favicon_oh.ico" />">

<!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap.theme.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"/>

   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/angular.min.js" />"></script>

</head>

<body>
	<div id="main-box">
		<header><img alt="Optum Logo"
		src="<c:url value="/resources/images/optumlogo.png" />"></header>
	
		<div>
		<form>
			<span class="formInput"><label>Job Name: </label> 
			<select id="job_name" name="job_name">
				<option>PSF_BIL_CL_1ST_WD_WK (PSFBIL05)</option>
				<option>PSF_BIL_CL_1ST_WD_WKPSF_ACR_CL_WD1_CHK (PSFACR15)</option>
			</select> </span>
			<span class="formInput"><label for="holiday_list">Holiday List:</label> 
			<input type="file" id="holiday_list" name="holiday_list" /> </span>
			<span class="formInput"><label>Year</label><input></span> 
		</form>
		
		</div>

		<footer>footer</footer>
	</div>
</body>
</html>