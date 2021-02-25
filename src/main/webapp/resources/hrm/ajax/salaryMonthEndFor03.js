function loadPayPeriod() {
	var x = document.getElementById("startDate").value;
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

			document.getElementById("startDateValue").value = dFormat;
			document.getElementById("endDateValue").value = dFormat2;
			document.getElementById("periodID").value = data.payPeriodID;

			loadPayCode3();
			$("#periodIDDiv").show();
			$("#startDateDiv").show();
			$("#endDateDiv").show();

		},
		error : function(e) {
			alert("Pay Period not Found");
		}
	});

	}

function loadPayCode3() {
var z = document.getElementById("periodID").value;
	
	var slctSubcat = $('#payCodeID'), option = "";
	slctSubcat.empty();
	
	$.ajax({
		type: "GET",
		url: "loadpayCodeID",
		data: {"periodID" : z},
		success:function(data) { 

			
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
		error:function(e) {
			alert("Not Found Pay Code");
		}
	});
}