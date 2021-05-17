function manageFields() {
	$("#payPeriodValDiv").hide();
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

	var slctSubcat = $('#payCodeID'), option = "";
	slctSubcat.empty();
	
	$.ajax({
		type : "GET",
		url : "loadpayCodeID",
		data : {
			"periodID" : z
		},
		success : function(data) {

			var slctSubcat = $('#payCodeID'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>--SELECT--</option>"
			slctSubcat.append(selected_option);
			
			
			
			for (var i = 0; i < data.length; i++) {
				option = option
						+ "<option value='"+data[i].payCodeID + "'>"
						+ data[i].payCode + "</option>";
			}
			
			slctSubcat.append(option);

		},
		error : function(e) {
			alert("Pay Code Not Found");
		}
	});
}
function visibleFields() {
	$("#payPeriodValDiv").slideDown();
}
