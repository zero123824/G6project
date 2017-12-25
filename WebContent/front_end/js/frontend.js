$(document).ready(function() {
	$("#sidebar").mCustomScrollbar({
		theme : "minimal"
	});

	$('#dismiss, .overlay').on('click', function() {
		$('#sidebar').removeClass('active');
		$('.overlay').fadeOut();
	});

	$('#sidebarCollapse').on('click', function() {
		$('#sidebar').addClass('active');
		$('.overlay').fadeIn();
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});

	// Initialize Tooltip
	$('[data-toggle="tooltip"]').tooltip();
	$(".navbar a, footer a[href='#myPage']").on('click', function(event) {

		// Make sure this.hash has a value before overriding default
		// behavior
		if (this.hash !== "") {

			// Prevent default anchor click behavior
			event.preventDefault();

			// Store hash
			var hash = this.hash;

			// Using jQuery's animate() method to add smooth page scroll
			// The optional number (900) specifies the number of
			// milliseconds it takes to scroll to the specified area
			$('html, body').animate({
				scrollTop : $(hash).offset().top
			}, 900, function() {
				// Add hash (#) to URL when done scrolling (default click
				// behavior)
				window.location.hash = hash;
			});
		} // End if
	});
});
