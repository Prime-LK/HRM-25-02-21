function loadPayPeriod() {
	var x = document.getElementById("startDate").value;
	var y = document.getElementById("endDate").value;

	$.ajax({
		type : "GET",
		url : "loadPeriodID",
		data : {
			"startDate" : x,
			"endDate" : y
		},
		success : function(data) {
			
			document.getElementById("periodID").value = data.payPeriodID;
			loadPayCode();
			$("#periodIDDiv").show();
			$('#payCodeDiv').show();

		},
		error : function(e) {
			alert("Pay Period Not Found");
		}
	});

	}

function loadPayCode() {
	var z = document.getElementById("periodID").value;
	$.ajax({
		type : "GET",
		url : "getPayCodeUsingPeriond",
		data : {
			"periodID" : z
		},
		success : function(data) {

			document.getElementById("payCodeID").value = data.payCodeID;
//			loadDetails();

		},
		error : function(e) {
			alert("ID Does not Exists");
		}
	});
}