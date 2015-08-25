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

});

jQuery.extend(jQuery.fn, {
	years : function(start, end) {
		for (i = start; i <= end; i++) {
			$(this).append($('<option />').val(i).html(i));
		}
	}
});