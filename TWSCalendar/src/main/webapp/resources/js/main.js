$(function() {
	var currentYear = new Date().getFullYear();
	$('#year-input').years(currentYear - 5, currentYear + 10);

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
//			console.log(data)
			$('#job_name').jobCodes(data);
//			loadToPayrollGrid(data, "#openPayrollGrid");
		},
		error : function(e) {
			// console.log(e);
		}
	});

});

jQuery.extend(jQuery.fn, {
	years : function(start, end) {
		for (i = start; i <= end; i++) {
			$(this).append($('<option />').val(i).html(i));
		}
	},

	jobCodes: function(data){
		var input = $(this)
		$.each(data, function(i, data) {
			input.append($('<option>', {
				value : data.jobCode,
				text : data.jobCode + " ("+ data.jobDescription +")"
			}));
		});
		
	}
});