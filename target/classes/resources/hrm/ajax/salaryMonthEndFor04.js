function manageFields() {
	$("#payPeriodValDiv").hide();
	$("#payCodeValDiv").hide();
	$("#startDateDiv").hide();
	$("#endDateDiv").hide();
	
	loadProcessYearAndMonth();
}

function loadProcessYearAndMonth() {
	$.ajax({
		type : "GET",
		url : "loadProcessYearAndMonth",
		success : function(data) {
			for(var i = 0; i < data.length; i++) {
				
				var a = new Date(data[i][1]);
				
				var y1 = a.getFullYear();
				var m1 = a.getMonth()+1;
				
				var format = y1 +'-'+ m1;
				
				$('#datepicker').val(format);
				loadPayPeriod();
			}
		},
		error : function(e) {
			
		}
	});
}

function loadPayPeriod() {
	var fieldVal = $('#datepicker').val();

	var year1 = new Date(fieldVal);
	var x = year1.getFullYear();
	var y = year1.getMonth() + 1;

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
			document.getElementById("periodIDVal").value = data.desc;

			loadPayCode();
			visibleFields()

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
			$("#payCodeID").empty();
			$("#payCodeIDVal").empty();
			document.getElementById("payCodeID").value = data.payCodeID;
			document.getElementById("payCodeIDVal").value = data.payCode;
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
	
}

function visibleFields() {
	$("#payPeriodValDiv").slideDown();
	$("#payCodeValDiv").slideDown();
	$("#startDateDiv").slideDown();
	$("#endDateDiv").slideDown();
}