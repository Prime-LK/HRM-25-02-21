function getCompletePage() {
	$("#periodIDDiv").hide();
	$("#payCodeDiv").hide();
	$("#startDateDiv").hide();
	$("#endDateDiv").hide();
	$('#processUserDiv').hide();
	$("#detailsTbl").hide();
	$("#sample").hide();
}

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
			loadRelatedEmpDetails();
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

function loadRelatedEmpDetails() {
	var x = document.getElementById("payCodeID").value;
	$.ajax({
		type : "GET",
		url : "getTableData01",
		data : {
			"payCodeID" : x
		},
		success : function(data) {

			$("#tableProcessPayroll tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" +
						  "<td>"+ data[i][0]+ "</td>"
						+ "<td>"+ data[i][1]+ "</td>"
						+ "<td>"+ data[i][2]+ "</td>"
						+ "<td>"+ data[i][3]+ "</td>"
						+ "<td>"+ data[i][4]+ "</td>"
						+ "<td><a onclick='getEmpDetailsRelatedPayCodeID1(); toggleDetails();'>" 
						+ "<img src='resources/img/more.png' width='25px' height='25px'></a></tr>";
				$("#tableProcessPayroll tbody").append(result);
			}
		},
		error : function(e) {
			alert("Table 01 Employee Data Not Found");
		}
	});
}


function getEmpDetailsRelatedPayCodeID1() {
	var x = document.getElementById("payCodeID").value;
	$.ajax({
		type : "GET",
		url : "getTableData02",
		data : {
			"payCodeID" : x
		},
		success : function(data) {

			$("#tableProcessPayroll1 tbody").empty();
			for (var i = 0; i < data.length; i++) {

				var result = "<tr>"
						+ "<td>"+ data[i][0]+ "</td>"
						+ "<td>"+ data[i][1]+ " " + data[i][2] + "</td>"
						+ "<td>"+ data[i][3]+ "</td>"
						+ "<td>"+ data[i][4]+ "</td>"
						+ "<td>"+ data[i][5]+ "</td>"
						+ "<td>"+ data[i][6]+ "</td>"
						+ "<td><input type='button' class='btn btn-success btn-sm' value='" + data[i][0]
						+ "' onclick='getEmpWithDetails1(this.value);togglePaySlip();getCalProritoy(this.value);'></td>"
						+ "</tr>";
				$("#tableProcessPayroll1").append(result);
			}
		},
		error : function(e) {
			alert("Table 02 Employee Data Not Found");
		}
	});
}

function getEmpWithDetails1(e) {
	// load 'more' button related fields
	var p = document.getElementById("payCodeID").value;
	$.ajax({
		type : "GET",
		url : "getTableData03",
		data : {
			"empID" : e,
			"payCodeID" : p
		},
		success : function(data) {

			$('#slipEmpID').val('');
			$('#empnameoftble3').val('');
			$('#empssoftble3').val('');
			$('#miniTable1 tbody').empty();
			$('#miniTable2 tbody').empty();
			$('#miniTable3 tbody').empty();

			for (var i = 0; i < data.length; i++) {				
				document.getElementById("empidoftble3").value = data[i][0];
				document.getElementById("empnameoftble3").value = data[i][1] + " " + data[i][2];
				document.getElementById("empssoftble3").value = data[i][3];			
				
				var result1 = 						
							"<tr><td id='miniRow1'>" + data[i][4] + "</td>"
							+ "<td id='miniRow2'>" + data[i][5] + "</td></tr>"
							
						    + "<tr><td id='miniRow1'>" + data[i][6] + "</td>"
							+ "<td id='miniRow2'>" + data[i][7] + "</td></tr>"
							
					        + "<tr><td id='miniRow1'>" + data[i][10] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][11] + "</td></tr>"
							
							+ "<tr><td id='miniRow1'>" + data[i][12] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][13] + "</td></tr>"
								
						    + "<tr><td id='miniRow1'>" + data[i][14] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][15] + "</td></tr>";
				
				            $("#miniTable1").append(result1);	
				                
				var result2 = 						
							"<tr><td id='miniRow1'>" + data[i][16] + "</td>"
							+ "<td id='miniRow2'>" + data[i][17] + "</td></tr>"
					
							+ "<tr><td id='miniRow1'>" + data[i][18] + "</td>"
							+ "<td id='miniRow2'>" + data[i][19] + "</td></tr>"
					
							+ "<tr><td id='miniRow1'>" + data[i][22] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][23] + "</td></tr>"
					
							+ "<tr><td id='miniRow1'>" + data[i][24] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][25] + "</td></tr>"
						
							+ "<tr><td id='miniRow1'>" + data[i][26] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][27] + "</td></tr>";
				
							$("#miniTable2").append(result2);	
			
				var result3 = 						
							"<tr><td id='miniRow1'>" + data[i][28] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][29] + "</td></tr>"
						
							+ "<tr><td id='miniRow1'>" + data[i][30] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][31] + "</td></tr>"
						
							+ "<tr><td id='miniRow1'>" + data[i][34] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][35] + "</td></tr>"
						
							+ "<tr><td id='miniRow1'>" + data[i][36] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][37] + "</td></tr>"
						
							+ "<tr><td id='miniRow1'>" + data[i][38] + "</td>" 
							+ "<td id='miniRow2'>" + data[i][39] + "</td></tr>";
						
							$("#miniTable3").append(result3);
			}

		},
		error : function(e) {
			alert("Details Error");
		}
	});
}
function getCalProritoy(e) {
	var x = document.getElementById("payCodeID").value;
	$.ajax({
		type : "GET",
		url : "calPriorityData",
		data : {
			"payCodeID" : x,
			"empID" : e
		},
		success : function(data1) {
			
			$.ajax({
				type : "GET",
				url : "otherGrossValues",
				data : {
					"empID" : e
				},
				success : function(data) {
					
					for(var i = 0; i < data.length; i++) {
						
						var result3 = 						
						    "<tr><td id='miniRow1'>" + data[i][0] + "</td>" 
							+ "<td id='miniRow2'>" + (data1 * data[i][1])/100 + "</td></tr>";
						
							$("#miniTable3").append(result3);
						
					}
					
				},
				error : function(e) {
					alert("Employee Other Percentage Values Not Found");
				}
			});
			
			$.ajax({
				type : "GET",
				url : "dedGrossPerValues",
				data : {
					"empID" : e
				},
				success : function(data2) {
					
					for(var i = 0; i < data2.length; i++) {
						
						var result2 = 						
						    "<tr><td id='miniRow1'>" + data2[i][0] + "</td>" 
							+ "<td id='miniRow2'>" + (data1 * data2[i][1])/100 + "</td></tr>";
						
							$("#miniTable2").append(result2);
						
					}
					
				},
				error : function(e) {
					alert("Employee Deduction Percentage Values Not Found");
				}
			});
			
			$.ajax({
				type : "GET",
				url : "addGrossPerValues",
				data : {
					"empID" : e
				},
				success : function(data3) {
					
					for(var i = 0; i < data3.length; i++) {
						
						var result1 = 						
						    "<tr><td id='miniRow1'>" + data3[i][0] + "</td>" 
							+ "<td id='miniRow2'>" + (data1 * data3[i][1])/100 + "</td></tr>";
						
							$("#miniTable1").append(result1);
						
					}
					
				},
				error : function(e) {
					alert("Employee Addition Percentage Values Not Found");
				}
			});
			
		},
		error : function(e) {
			alert("Employee Calculation Priority Not Found");
		}
	});
}

function toggleDetails() {
	$("#detailsTbl").slideToggle();
	$("#sample").slideUp();
}

function togglePaySlip() {
	$("#sample").slideDown();
}