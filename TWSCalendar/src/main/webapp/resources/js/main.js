$(function() {
	var currentYear = new Date().getFullYear();
	$('#year-input').years(currentYear-5, currentYear+10);
	
	var generateBtn = $('#generate_btn');
	
	generateBtn.on('click', function() {
		window.location = "/TWSCalendar/viewCalendar";
	});
});


jQuery.extend(jQuery.fn,{
	years: function(start, end) {
	    for (i = start; i <= end; i++)
	    {
	        $(this).append($('<option />').val(i).html(i));
	    }
	}
});