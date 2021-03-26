function getCompletePage() {
	$("#payPeriodValDiv").hide();
	$('#payCodeValDiv').hide();
	$("#detailsTbl1Column").hide();
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
			fieldsVisible();

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
			loadDetails();
		},
		error : function(e) {
			alert("ID Does not Exists");
		}
	});
}

function loadDetails() {
	var x = document.getElementById("payCodeID").value;
	$
	.ajax({
		type : "GET",
		url : "getTableData01",
		data : {
			"payCodeID" : x
		},
		success : function(data) {
			$("#detailsTbl1").empty();
			for (var i = 0; i < data.length; i++) {
					var result =  '<li class="list-group-item">'+ data[i][0] +'</li>'
								+ '<li class="list-group-item">'+ data[i][1] +'</li>'
								+ '<li class="list-group-item">'+ data[i][2] +'</li>'
								+ '<li class="list-group-item">'+ data[i][3] +'</li>'
								+ '<li class="list-group-item">'+ data[i][4] +'</li>';
								$("#detailsTbl1").append(result);

			} 
			toggleDetailsTbl1();
		},
		error : function(e) {
			alert("Table 01 Employee Data Not Found");
		}
	});
}

function getEmpDetailsRelatedPayCodeID1(e) {	
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
	var q = document.getElementById("comID").value;
	$.ajax({
		type : "GET",
		url : "getTableData03",
		data : {
			"empID" : e,
			"payCodeID" : p,
			"comID" : q
		},
		success : function(data) {
			$("#additions").empty();
			$("#deductions").empty();
			$("#others").empty();
			for (var i = 0; i < data.length; i++) {
				$("#empidoftble3").text(data[i][0]);
				$("#empnameoftble3").text(data[i][1] + " " + data[i][2]);
				$("#empssoftble3").text(data[i][3]);
				
				if(data[i][i] == '') {
					$("span:empty").remove();
				}	
				var result1 = 	
							  '<span class="col-7">'+ data[i][4] +'</span>'
							+ '<span class="col-5">'+ data[i][5] +'</span>'
							
							+ '<span class="col-7">'+ data[i][6] +'</span>'
							+ '<span class="col-5">'+ data[i][7] +'</span>'
							
							+ '<span class="col-7">'+ data[i][10] +'</span>'
							+ '<span class="col-5">'+ data[i][11] +'</span>'
							
							+ '<span class="col-7">'+ data[i][12] +'</span>'
							+ '<span class="col-5">'+ data[i][13] +'</span>'
							
							+ '<span class="col-7">'+ data[i][14] +'</span>'
							+ '<span class="col-5">'+ data[i][15] +'</span>';	
				
				            $("#additions").append(result1);
				                
				var result2 =
						      '<span class="col-7">'+ data[i][16] +'</span>'
							+ '<span class="col-5">'+ data[i][17] +'</span>';
						
							+ '<span class="col-7">'+ data[i][18] +'</span>'
							+ '<span class="col-5">'+ data[i][19] +'</span>'
						
							+ '<span class="col-7">'+ data[i][22] +'</span>'
							+ '<span class="col-5">'+ data[i][23] +'</span>'
						
							+ '<span class="col-7">'+ data[i][24] +'</span>'
							+ '<span class="col-5">'+ data[i][25] +'</span>'
							
							+ '<span class="col-7">'+ data[i][26] +'</span>'
							+ '<span class="col-5">'+ data[i][27] +'</span>';
				
							$("#deductions").append(result2);	
			
				var result3 = 			
						      '<span class="col-7">'+ data[i][28] +'</span>'
							+ '<span class="col-5">'+ data[i][29] +'</span>'
						
							+ '<span class="col-7">'+ data[i][30] +'</span>'
							+ '<span class="col-5">'+ data[i][31] +'</span>'
						
							+ '<span class="col-7">'+ data[i][34] +'</span>'
							+ '<span class="col-5">'+ data[i][35] +'</span>'
						
							+ '<span class="col-7">'+ data[i][36] +'</span>'
							+ '<span class="col-5">'+ data[i][37] +'</span>'
							
							+ '<span class="col-7">'+ data[i][38] +'</span>'
							+ '<span class="col-5">'+ data[i][39] +'</span>';
						
							$("#others").append(result3);

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
							  '<span class="col-7">'+ data[i][0] +'</span>'
							+ '<span class="col-5">'+ (data1 * data[i][1])/100 +'</span>';
						$("#others").append(result3);
						
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
							  '<span class="col-7">'+ data2[i][0] +'</span>'
							+ '<span class="col-5">'+ (data1 * data2[i][1])/100 +'</span>';
					
						$("#deductions").append(result2);
						
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
						      '<span class="col-7">'+ data3[i][0] +'</span>'
							+ '<span class="col-5">'+ (data1 * data3[i][1])/100 +'</span>';						
						$("#additions").append(result1);
						
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

function toggleDetailsTbl1() {
	$("#detailsTbl1Column").slideDown();
}

function toggleDetailsTbl2() {
	$("#detailsTbl").slideDown();
}

function togglePaySlip() {
	$("#sample").slideDown();
}

function fieldsVisible() {
	$("#payPeriodValDiv").slideDown();
	$("#payCodeValDiv").slideDown();
}