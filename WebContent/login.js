/**
 * 
 */

$(document).ready(function() {
	if(window.location.href.indexOf("?error=1") > -1) {
		$('span.label-warning').show();
	}
	else {
		$('span.label-warning').hide();
	}
});