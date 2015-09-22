<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PeopleSoft | TWS Calendar Generator</title>

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
				PeopleSoft | TWS Calendar Generator</span>
		</header>

		<div id="content-box">

			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">PeopleSoft</a></li>
					<li><a href="#tabs-2">Datamart</a></li>
				</ul>
				<div id="tabs-1">
					<form id="file_gen_form" action="/TWSCalendar/generateFile"
						method="post">
						<fieldset>
							<span class="formInput"><label>Job Name : </label> <select
								class="form-control" id="job_name" name="jobname" required>
							</select> </span> <span class="formInput"><label>Year : </label> <select
								class="form-control" name="year" id="year-input" required></select></span>

							<span class="formInput"> <label for="holiday_name">Holiday
									List:</label> <input class="form-control" type="text" id="holiday_name"
								value="Name"
								onblur="if(this.value==''){ this.value='Name'; this.style.color='#BBB';}"
								onfocus="if(this.value=='Name'){ this.value=''; this.style.color='#555';}"
								style="color: #BBB;" /> <input class="form-control" type="text"
								id="holiday_date" value="Date" />

								<button type="button" class="btn btn-warning"
									id="add_holidayBtn" onclick="addHoliday()">Add Date</button>
								<table id="holidayDatesGrid"></table>
								<button type="button" class="btn btn-danger"
									id="delete_holidayBtn" onclick="deleteHoliday()" disabled>Delete</button>
							</span> <input name="fileType" id="file_type" type="hidden"
								name="fileType" /> <input name="holidayList" id="holiday_list"
								type="hidden" name="holidayList" />

						</fieldset>
					</form>
					<div class="spacer">
						<button type="button" class="btn btn-primary" id="generate_btn">Generate
							.xls</button>
						<button type="button" class="btn btn-info" id="gen_txt_btn">Generate
							.txt</button>
					</div>
				</div>
				<div id="tabs-2">
					<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus
						gravida ante, ut pharetra massa metus id nunc. Duis scelerisque
						molestie turpis. Sed fringilla, massa eget luctus malesuada, metus
						eros molestie lectus, ut tempus eros massa ut dolor. Aenean
						aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit
						aliquam. Praesent in eros vestibulum mi adipiscing adipiscing.
						Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel
						metus. Ut posuere viverra nulla. Aliquam erat volutpat.
						Pellentesque convallis. Maecenas feugiat, tellus pellentesque
						pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel
						felis. Mauris consectetur tortor et purus.</p>
				</div>

			</div>



		</div>

		<footer>&copy; Optum, Inc. All rights reserved.</footer>
	</div>
</body>
</html>