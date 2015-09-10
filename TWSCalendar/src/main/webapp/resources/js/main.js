$(function() {
	var selectedYear = $('#year-input');
	var currentYear = new Date().getFullYear();
	selectedYear.years(currentYear - 3, currentYear + 5);
	selectedYear.val(currentYear);
	
	jQuery.validator.setDefaults({
		success : "valid"
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
	})

	var submitform = function() {
		var fileGeneratorForm = $("#file_gen_form");
		
		var holidayTable = $('#holidayDatesGrid');
		holidayTable.bootstrapTable('checkAll');
		var selects = holidayTable.bootstrapTable('getAllSelections');	

		$("#holiday_list").val(JSON.stringify(selects));
		alert($("#holiday_list").val())
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

	$.ajax({
		url : "/TWSCalendar/getJobCodes",
		type : "GET",
		accept : 'application/json',
		success : function(data) {
			// console.log(data)
			$('#job_name').jobCodes(data);
			// loadToPayrollGrid(data, "#openPayrollGrid");
		},
		error : function(e) {
			// console.log(e);
		}
	});


	$("#holiday_date").datepicker();

	var holidayTable = $('#holidayDatesGrid');
	holidayTable.bootstrapTable({
		height : 300,
		striped : true,
		pagination : true,
		pageSize : 50,
		pageList : [ 10, 25, 50, 100, 200 ],
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
	rowCtr = 0;
});
var rowCtr;

var disableDelete = function(boolean) {
	$('#delete_holidayBtn').prop("disabled", boolean)
}
var addHoliday = function() {
	var name = $('#holiday_name');
	var date = $('#holiday_date');
	if(name.val()=="Name" || date.val()=="Date"){
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