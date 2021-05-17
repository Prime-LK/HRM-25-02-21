//get report based on joined date 
function myFunction() {
	var y = document.getElementById("joinedDate");
	var x = document.getElementById("joinedDate2");
	
	$.ajax({
		type: "GET",
		url: "submitjoinedDateBasedReport",
		data: {"joinedDate" : y.value ,"joinedDate2" : x.value },
		success:function(data) {			
				window.location.href = "submitjoinedDateBasedReport?joinedDate=" + y.value+ "&joinedDate2=" + x.value;		
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}
//get relevant employee based on resign date to report 
function myFunction2() {
	var y = document.getElementById("resignDate");
	var x = document.getElementById("resignDate2");
	$.ajax({
		type: "GET",
		url: "submitresignDateBasedReport",
		data: {"resignDate" : y.value ,"resignDate2" : x.value },
		success:function(data) {		
				window.location.href = "submitresignDateBasedReport?resignDate=" + y.value+ "&resignDate2=" + x.value;		
		},
		error:function(e) {
			alert("Employee Does not Exists");
		}
	});
}
