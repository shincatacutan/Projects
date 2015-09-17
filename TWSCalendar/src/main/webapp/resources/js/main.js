$(function() {
	initFormFields();
	populateJobCodes();
	initHolidayGrid();
});
var rowCtr;

var disableDelete = function(boolean) {
	$('#delete_holidayBtn').prop("disabled", boolean)
}

var populateHolidayGrid = function(data) {
	var year = $("#year-input").val();
	var holidayTable = $('#holidayDatesGrid');

	$.ajax({
		url : "/TWSCalendar/getHolidayList",
		type : "GET",
		accept : 'application/json',
		data : {
			'year' : year
		},
		success : function(data) {
			console.log(data);
			holidayTable.bootstrapTable('load', data);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var populateJobCodes = function() {
	$.ajax({
		url : "/TWSCalendar/getJobCodes",
		type : "GET",
		accept : 'application/json',
		success : function(data) {
			$('#job_name').jobCodes(data);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var initHolidayGrid = function() {
	var year = $("#year-input").val();
	var holidayTable = $('#holidayDatesGrid');
	holidayTable.bootstrapTable({
		height : 300,
		striped : true,
		pagination : false,
		url : "/TWSCalendar/getHolidayList",
		dataType : 'json',
		queryParams : function(params) {
			return "year=" + year;
		},
		minimumCountColumns : 2,
		clickToSelect : true,
		columns : [ {
			field : 'state',
			checkbox : true
		}, {
			field : 'name',
			title : 'Name',
			align : 'left',
			valign : 'middle',
			sortable : true
		}, {
			field : 'date',
			title : 'Date',
			align : 'left',
			valign : 'middle',
			sortable : true
		} ],
		onCheckAll : function() {
			disableDelete(false)
		},
		onCheck : function() {
			disableDelete(false)
		},
		onClickRow : function() {
			disableDelete(false)
		},
		onUncheck : function() {
			var selections = holidayTable.bootstrapTable('getSelections')
			if (selections.length == 0) {
				disableDelete(true);
			}
		},
		onUncheckAll : function() {
			disableDelete(true)
		}
	});
}
var addHoliday = function() {
	var name = $('#holiday_name');
	var date = $('#holiday_date');
	if (name.val() == "Name" || date.val() == "Date") {
		alert("Please input name and date of the holiday.");
		return false;
	}
	var holidayTable = $('#holidayDatesGrid');
	holidayTable.bootstrapTable('insertRow', {
		index : rowCtr++,
		row : {
			name : name.val(),
			date : date.val()
		}
	});
	name.val("Name");
	date.val("Date");
}

var deleteHoliday = function() {
	var holidayTable = $('#holidayDatesGrid');
	var selects = holidayTable.bootstrapTable('getSelections');
	ids = $.map(selects, function(row) {
		return row.name;
	});

	holidayTable.bootstrapTable('remove', {
		field : 'name',
		values : ids
	});

	disableDelete(true);
}

jQuery.extend(jQuery.fn, {
	years : function(start, end) {
		for (i = start; i <= end; i++) {
			$(this).append($('<option />').val(i).html(i));
		}
	},

	jobCodes : function(data) {
		var input = $(this)
		$.each(data, function(i, data) {
			input.append($('<option>', {
				value : data.jobCode,
				text : data.jobCode + " (" + data.jobDescription + ")"
			}));
		});

	}
});

var initFormFields = function() {
	var selectedYear = $('#year-input');
	var currentYear = new Date().getFullYear();
	selectedYear.years(currentYear - 1, currentYear + 3);
	selectedYear.val(currentYear);

	jQuery.validator.setDefaults({
		success : "valid"
	});

	$("#holiday_date").datepicker();
	$("#holiday_date").on('blur', function() {
		if (this.value == '') {
			this.value = 'Date';
			this.style.color = '#BBB';
		} else {
			this.style.color = '#555'
		}
	});

	$("#holiday_date").on('focus', function() {
		if (this.value == 'Date') {
			this.value = '';
			this.style.color = '#555';
		} else {
			this.style.color = '#BBB';
		}
	});

	var generateBtn = $('#generate_btn');

	generateBtn.on('click', function() {
		$("#file_type").val("xlsx");
		submitform();
	});

	var generateTxtBtn = $('#gen_txt_btn');
	generateTxtBtn.on('click', function() {
		$("#file_type").val("txt");
		submitform();
	});
	rowCtr = 0;
	
	var yearInput = $("#year-input");
	yearInput.on('change', function(){
		populateHolidayGrid();
	});
}

var submitform = function() {
	var fileGeneratorForm = $("#file_gen_form");

	var holidayTable = $('#holidayDatesGrid');
	holidayTable.bootstrapTable('checkAll');
	var selects = holidayTable.bootstrapTable('getAllSelections');

	if (selects.length == 0) {
		alert("Please add holiday list");
		return false;
	}

	$("#holiday_list").val(JSON.stringify(selects));
	fileGeneratorForm.validate({
		onsubmit : false,
		submitHandler : function(form) {
			if ($(form).valid()) {
				form.submit();
			}
			return false;
		}
	});
	if (fileGeneratorForm.valid()) {
		fileGeneratorForm.submit();
	}
}