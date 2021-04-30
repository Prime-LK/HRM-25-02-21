function getCompletePage() {
	$("#payPeriodValDiv").hide();
	$("#payCodeValDiv").hide();
	$("#startDateDiv").hide();
	$("#endDateDiv").hide();
	$("#detailsTbl1Column").hide();
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

			//set values for spans and inputs
			document.getElementById("periodID").value = data.payPeriodID;
			document.getElementById("startDate").value = dFormat;
			document.getElementById("endDate").value = dFormat2;
			document.getElementById("periodIDVal").value = data.desc;
			
			//functions calling 
			loadPayCode();
			toggleDetailsTbl1();
			fieldsVisible();
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
			document.getElementById("payCodeIDVal").value = data.payCode;
			loadRelatedEmpDetails();			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
	
}

function loadRelatedEmpDetails() {
	var x = document.getElementById("payCodeID").value;
	var f = document.getElementById("comID").value;
	$.ajax({
		type : "GET",
		url : "getTableData01",
		data : {
			"payCodeID" : x,
			"comID" : f
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
		},
		error : function(e) {
			alert("Table 01 Employee Data Not Found");
		}
	});
}


function getEmpDetailsRelatedPayCodeID1() {
	var x = document.getElementById("payCodeID").value;
	var f = document.getElementById("comID").value;
	$.ajax({
		type : "GET",
		url : "getTableData02",
		data : {
			"payCodeID" : x,
			"comID" : f
		},
		success : function(data) {
			$("#tableProcessPayroll1 tbody").empty();
				for(var i=0; i < data.length; i++) {
				var result = "<tr>"
						+ "<td id='tbl02Data'>"+ data[i][0]+ "</td>"
						+ "<td id='tbl02Data'>"+ data[i][1]+ " " + data[i][2] + "</td>"
						+ "<td id='tbl02Data'>"+ data[i][3]+ "</td>"
						+ "<td id='tbl02Data'>"+ data[i][4]+ "</td>"
						+ "<td id='tbl02Data'>"+ data[i][5]+ "</td>"
						+ "<td id='tbl02Data'>"+ data[i][6]+ "</td>"
						+ "<td><input type='button' class='btn btn-success btn-sm'"
						+ "value='" + data[i][0] + "' onClick='getEmpWithDetails1(this.value);" 
						+ "getCalProritoy(this.value);calculatePerValuesInBasicSalary(this.value)' />"
						+ "</td></tr>";
				$("#tableProcessPayroll1 tbody").append(result);
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
										  '<span id="additionData" class="col-7">'+ data[i][4] +'</span>'
										+ '<span id="additionData" class="col-5">'+ data[i][5] +'</span>'
										+ '<span id="additionData" class="col-7">'+ data[i][6] +'</span>'
										+ '<span id="additionData" class="col-5">'+ data[i][7] +'</span>'
										+ '<span id="additionData" class="col-7">'+ data[i][8] +'</span>'
										+ '<span id="additionData" class="col-5">'+ data[i][9] +'</span>'
										+ '<span id="additionData" class="col-7">'+ data[i][10] +'</span>'
										+ '<span id="additionData" class="col-5">'+ data[i][11] +'</span>';	
							            $("#additions").append(result1);
							                
							var result2 =
									      '<span id="deductionData" class="col-7">'+ data[i][12] +'</span>'
										+ '<span id="deductionData" class="col-5">'+ data[i][13] +'</span>'
										+ '<span id="deductionData" class="col-7">'+ data[i][14] +'</span>'
										+ '<span id="deductionData" class="col-5">'+ data[i][15] +'</span>'
										+ '<span id="deductionData" class="col-7">'+ data[i][16] +'</span>'
										+ '<span id="deductionData" class="col-5">'+ data[i][17] +'</span>'
										+ '<span id="deductionData" class="col-7">'+ data[i][18] +'</span>'
										+ '<span id="deductionData" class="col-5">'+ data[i][19] +'</span>';
										$("#deductions").append(result2);	
						
							var result3 = 			
									      '<span id="otherData" class="col-7">'+ data[i][20] +'</span>'
										+ '<span id="otherData" class="col-5">'+ data[i][21] +'</span>'
										+ '<span id="otherData" class="col-7">'+ data[i][22] +'</span>'
										+ '<span id="otherData" class="col-5">'+ data[i][23] +'</span>'
										+ '<span id="otherData" class="col-7">'+ data[i][24] +'</span>'
										+ '<span id="otherData" class="col-5">'+ data[i][25] +'</span>'
										+ '<span id="otherData" class="col-7">'+ data[i][26] +'</span>'
										+ '<span id="otherData" class="col-5">'+ data[i][27] +'</span>';
										$("#others").append(result3);

						}
		},
		error : function(e1) {
			alert("Per Employee Details Not Found");
		}
	});
}
function getCalProritoy(e) {
	var x = document.getElementById("payCodeID").value;
	var f = document.getElementById("comID").value;
	$.ajax({
		type : "GET",
		url : "calPriorityData",
		data : {
			"payCodeID" : x,
			"empID" : e,
			"comID" : f
		},
		success : function(data1) {
			$.ajax({
				type : "GET",
				url : "otherGrossValues",
				data : {
					"empID" : e,
					"comID" : f
				},
				success : function(data) {
					
					for(var i = 0; i < data.length; i++) {
						
						var result3 = 	
								  '<span id="otherData" class="col-7">'+ data[i][0] +'</span>'
								+ '<span id="otherData" class="col-5">'+ (data1 * data[i][1])/100 +'</span>';
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
					"empID" : e,
					"comID" : f
				},
				success : function(data2) {
					
					for(var i = 0; i < data2.length; i++) {
						
						var result2 = 			
								  '<span id="deductionData" class="col-7">'+ data2[i][0] +'</span>'
								+ '<span id="deductionData" class="col-5">'+ (data1 * data2[i][1])/100 +'</span>';
						
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
					"empID" : e,
					"comID" : f
				},
				success : function(data3) {
					console.log(data3);
					for(var i = 0; i < data3.length; i++) {
						
						var result1 = 
							      '<span id="additionData" class="col-7">'+ data3[i][0] +'</span>'
								+ '<span id="additionData" class="col-5">'+ (data1 * data3[i][1])/100 +'</span>';						
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

function calculatePerValuesInBasicSalary(e) {
	var x = document.getElementById("comID").value;
	$.ajax({
		type : "GET",
		url : "addBasicPerValues",
		data : {
			"comID" : x,
			"empID" : e
		},
		success : function(data1) {
			$.ajax({
				type : "GET",
				url : "othBasicPerValuesBasic",
				data : {
					"empID" : e,
					"comID" : x
				},
				success : function(data) {
					
					for(var i = 0; i < data.length; i++) {
						
						var result3 = 	
								  '<span id="otherData" class="col-7">'+ data[i][0] +'</span>'
								+ '<span id="otherData" class="col-5">'+ (data1 * data[i][1])/100 +'</span>';
							$("#others").append(result3);
						
					}
					
				},
				error : function(e) {
					alert("Employee Other Percentage Values Not Found");
				}
			});
			
			$.ajax({
				type : "GET",
				url : "dedBasicPerValuesBasic",
				data : {
					"empID" : e,
					"comID" : x
				},
				success : function(data2) {
					
					for(var i = 0; i < data2.length; i++) {
						
						var result2 = 			
								  '<span id="deductionData" class="col-7">'+ data2[i][0] +'</span>'
								+ '<span id="deductionData" class="col-5">'+ (data1 * data2[i][1])/100 +'</span>';
						
							$("#deductions").append(result2);
						
					}
					
				},
				error : function(e) {
					alert("Employee Deduction Percentage Values Not Found");
				}
			});
			
			$.ajax({
				type : "GET",
				url : "addBasicPerValuesBasic",
				data : {
					"empID" : e,
					"comID" : x
				},
				success : function(data4) {
					
					for(var i = 0; i < data4.length; i++) {
						var result1 = 
							      '<span id="additionData" class="col-7">'+ data4[i][0] +'</span>'
								+ '<span id="additionData" class="col-5">'+ (data1 * data4[i][1])/100 +'</span>';						
							$("#additions").append(result1);
						
					}
					
				},
				error : function(e) {
					alert("Employee Addition Percentage Values Not Found");
				}
			});
			
		},
		error : function(e) {
			alert("Error Found Calculation in percentage allowance in basic salary");
		}
	});
}

function toggleDetailsTbl1() {
	$("#detailsTbl1Column").slideDown();
}

function fieldsVisible() {
	$("#payPeriodValDiv").slideDown();
	$("#payCodeValDiv").slideDown();
	$("#startDateDiv").slideDown();
	$("#endDateDiv").slideDown();
}
