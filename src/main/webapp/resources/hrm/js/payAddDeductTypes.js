function checkIfPercentage2() {
	if(document.getElementById('isPercentage2').value == 'value') {
		
		$("#percentage1").hide();
//		$("#percentage1").css({"width" : "100px"});
	
	} else {
		
		alert('Error Found When Selected Value Type');

	}
}

function checkIfPercentage() {
	if(document.getElementById('isPercentage').value == 'percentage') {
		
		$("#percentage1").show();
//		$("#percentage1").css({"width" : "40px"});
	
	} else {
		
		alert('Error Found When Selected Percentage Type');
		
	}
}