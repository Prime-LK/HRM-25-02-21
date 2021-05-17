function manageFields() {
	$("#payPeriodValDiv").hide();
	$("#payCodeValDiv").hide();
}
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
			document.getElementById("periodIDVal").value = data.desc;
			loadPayCode();
			visibleFields();
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
			document.getElementById("payCodeIDVal").value = data.payCode;
		},
		error : function(e) {
			alert("ID Does not Exists");
		}
	});
}
function visibleFields() {
	$("#payPeriodValDiv").slideDown();
	$("#payCodeValDiv").slideDown();
}