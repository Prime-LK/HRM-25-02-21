function loadPayPeriod() {
	var x = document.getElementById("year").value;
	var y = document.getElementById("sa").value;

	$.ajax({
		type : "GET",
		url : "getRePCodes",
		data : {
			"startDate" : x,
			"endDate" : y
		},
		success : function(data) {

			var a = new Date(data.startDate);
			var b = new Date(data.endDate);

			// startDate
			var gg1 = a.getFullYear();
			var gg2 = a.getMonth() + 1;
			var gg3 = a.getDate(data.startDate);

			// endDate
			var dd1 = a.getFullYear();
			var dd2 = a.getMonth() + 1;
			var dd3 = b.getDate(data.endDate);

			var dFormat = gg1 + '-' + gg2 + '-' + gg3;
			var dFormat2 = dd1 + '-' + dd2 + '-' + dd3;

			document.getElementById("startDate").value = dFormat;
			document.getElementById("endDate").value = dFormat2;
			document.getElementById("periodID").value = data.payPeriodID;

			loadPayCode();

		},
		error : function(e) {
			alert("Pay Code not Found");
		}
	});

	}

function loadPayCode() {
var z = document.getElementById("periodID").value;
	
	$.ajax({
		type: "GET",
		url: "getPayCodeUsingPeriond",
		data: {"periodID" : z},
		success:function(data) { 

			document.getElementById("payCodeID").value = data.payCodeID;
//			loadRelatedEmpDetails();
			$("#periodIDDiv").show();
			$("#payCodeDiv").show();
			$("#startDateDiv").show();
			$("#endDateDiv").show();
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
	
}