//get header with related fields
function getDefaultHeader() {
	$.ajax({	
				type : "GET",
				url : "loadRelatedHeader",
				success : function(data) {
					if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'Yes') {

						window.location.href = "getEmpMonthSalaryDetailsPage01";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'Yes') {
						
							window.location.href = "getEmpMonthSalaryDetailsPage03";
						    

					} else if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'No') {

						window.location.href = "getEmpMonthSalaryDetailsPage02";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'No') {
						
							window.location.href = "getEmpMonthSalaryDetailsPage04";

					}
				},
				error : function(e) {
					alert("Error found when data comming to page");
				}
			});
}


//function validateDropDown() {
//		if (document.getElementById("inlineRadio1").checked) {
//			document.getElementById("depID").disabled = false;
//		} else if (document.getElementById("inlineRadio2").checked) {
//			document.getElementById("loid").disabled = false;
//		} else if (document.getElementById("inlineRadio3").checked) {
//			document.getElementById("catgoryID").disabled = false;
//		} else if (document.getElementById("inlineRadio4").checked) {
//			document.getElementById("tid").disabled = false;
//		} else if (document.getElementById("inlineRadio6").checked) {
//			document.getElementById("empID").disabled = false;
//		}
//
//	}
//	function loadRelatedLoc() {
//
//	}
//function validateSelectField() {
//		document.getElementById("depID").disabled = true;
//		document.getElementById("loid").disabled = true;
//		document.getElementById("catgoryID").disabled = true;
//		document.getElementById("tid").disabled = true;
//		document.getElementById("empID").disabled = true;
//	}
//
//function loadEmpToTable() {
//	var y = document.getElementById("empID").value;
//	$.ajax({
//		type : "GET",
//		url : "loadEmpTable",
//		data : {
//			"empID" : y
//		},
//		success : function(data) {
//
//			$("#tableMoSaDetails tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//						"</td><td></td><td><input name='empidTable' id='empidTable'" +
//						"value=" + data[i].empID
//						+ " readOnly></td><td>" + data[i].name + " " + data[i].lastname
//						+ "</td><td><input name='amount'" +
//						" ></td></tr>";
//				$("#tableMoSaDetails tbody").append(result);
//				
//				 $("#tEmpID1").empty();
//				 var a = document.getElementById("tEmpID1");
//				 a.setAttribute("value", data[i].empID);
//
//			}
//		},
//		error : function(e) {
//			alert("Not Found Employees Or Departments");
//		}
//	});
//}
//
//function loadAddDed() {
//	var y = document.getElementById("deductTypeCode").value;
//	$.ajax({
//		type : "GET",
//		url : "loadAddDedType",
//		data : {
//			"deductTypeCode" : y
//		},
//		success : function(data) {
//
//			$("#addDeType").empty();
//			var a = document.getElementById("addDeType");
//			a.setAttribute("value", data.addDeType);
//		},
//		error : function(e) {
//			alert("Not Found Addition or Deduction Type");
//		}
//	});
//}
//
////load emp first select add/ded after emp dep
//function loadRelatedDep() {
//	var y = document.getElementById("depID").value;
//	var z = document.getElementById("deductTypeCode").value;
//	$.ajax({
//		type : "GET",
//		url : "loadReDep",
//		data : {
//			"dep" : y,
//			"deductTypeCode" : z
//		},
//		success : function(data) {
//				
//			$("#tableMoSaDetails tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td><input type='checkbox' id='cb1' onchange='setValues()'" +
//						" name='cb1' value='inactive'>" +
//						"</td>" +
//						"<td><input name='monthDePk.empID.empID' id='empidTable'" +
//						"value=" + data[i].empdetailPK.empID.empID
//						+ " readOnly></td><td>" + data[i].empdetailPK.empID.name + ""
//						+ data[i].empdetailPK.empID.lastname + "</td><td><input name='amount' class='amount'" +
//						 + " ></td></tr>";
//								
//					$("#tableMoSaDetails tbody").append(result);
//			}
////			$(document).on('change', '#cb1', function() {
////			    $(this).closest('tr').find('#empidTable, #tName, .amount').prop('disabled', !this.checked);
////			});
//		},
//		error : function(e) {
//			alert("Not Found Employees Or Departments");
//		}
//	});
//}
//
////load emp first select add/ded after emp loc
//function loadRelatedLoc() {
//	var z = document.getElementById("deductTypeCode").value;
//	var y = document.getElementById("loid").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedLocation",
//				data : {
//					"loid" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' onchange='setValues()'" +
//								" id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID'"
//								+ "value=" + data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Location");
//				}
//			});
//}
//
////load emp first select add/ded after emp type
//function loadRelatedType() {
//	var y = document.getElementById("tid").value;
//	var z = document.getElementById("deductTypeCode").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedType",
//				data : {
//					"tid" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID' value="
//								+ data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//						
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Employee Type");
//				}
//			});
//}
//
////load emp first select add/ded after emp cat
//function loadRelatedCat() {
//	var z = document.getElementById("deductTypeCode").value;
//	var y = document.getElementById("catgoryID").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedCat",
//				data : {
//					"catgoryID" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID' value="
//								+ data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//
//						var result2 = "<input type='hidden' name='monthDePk.empID.empID' " +
//						"value=" + data[i].empdetailPK.empID.empID + ">";
//						$("#tEmpID").append(result2);
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Employee Type");
//				}
//			});
//}
//
//
////load emp first select dep after emp add/ded
//function loadEmpRelatedList() {	
//	var y = document.getElementById("depID").value;
//	var z = document.getElementById("deductTypeCode").value;
//	$.ajax({
//		type : "GET",
//		url : "loadReDep",
//		data : {
//			"dep" : y,
//			"deductTypeCode" : z
//		},
//		success : function(data) {
//				
//			$("#tableMoSaDetails tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td><input type='checkbox' id='cb1' onchange='setValues()'" +
//						" name='cb1' value='inactive'>" +
//						"</td>" +
//						"<td><input name='monthDePk.empID.empID' id='empidTable'" +
//						"value=" + data[i].empdetailPK.empID.empID
//						+ " ></td><td>" + data[i].empdetailPK.empID.name + " "
//						+ data[i].empdetailPK.empID.lastname + "</td><td><input name='amount' class='amount'" +
//						 + " ></td></tr>";
//				$("#tableMoSaDetails tbody").append(result);
//				
//			}
//
//		},
//		error : function(e) {
//			alert("Not Found Employees Or Departments");
//		}
//	});
//}
//
////load emp first select loc after emp add/ded
//function loadEmpRelatedList2() {	
//	var z = document.getElementById("deductTypeCode").value;
//	var y = document.getElementById("loid").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedLocation",
//				data : {
//					"loid" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' onchange='setValues()'" +
//								" id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID'"
//								+ "value=" + data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Location");
//				}
//			});
//}
//
////load emp first select cat after emp  add/ded
//function loadEmpRelatedList3() {	
//	var z = document.getElementById("deductTypeCode").value;
//	var y = document.getElementById("catgoryID").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedCat",
//				data : {
//					"catgoryID" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID' value="
//								+ data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//
//						var result2 = "<input type='hidden' name='monthDePk.empID.empID' " +
//						"value=" + data[i].empdetailPK.empID.empID + ">";
//						$("#tEmpID").append(result2);
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Employee Type");
//				}
//			});
//}
//
////load emp first select type after emp add/ded
//function loadEmpRelatedList4() {	
//	var y = document.getElementById("tid").value;
//	var z = document.getElementById("deductTypeCode").value;
//	$
//			.ajax({
//				type : "GET",
//				url : "loadEmpRelatedType",
//				data : {
//					"tid" : y,
//					"deductTypeCode" : z
//				},
//				success : function(data) {
//					$("#tableMoSaDetails tbody").empty();
//					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//								"</td><td><input name='monthDePk.empID.empID' value="
//								+ data[i].empdetailPK.empID.empID
//								+ " ></td><td>"
//								+ data[i].empdetailPK.empID.name
//								+ " "
//								+ data[i].empdetailPK.empID.lastname
//								+ "</td><td><input name='amount'" +
//								+ " ></td></tr>";
//						$("#tableMoSaDetails tbody").append(result);
//						
//					}
//				},
//				error : function(e) {
//					alert("Not Found Employees Or Employee Type");
//				}
//			});
//}
//
//
//
//function loadAllEmps() {
//	if (document.getElementById("inlineRadio5").checked) {
//		$.ajax({
//			type : "GET",
//			url : "loadAllEmployeesToGrid",
//			success : function(data) {
//				$("#tableMoSaDetails tbody").empty();
//				for (var i = 0; i < data.length; i++) {
//					var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
//							"</td><td><input name='monthDePk.empID.empID' " +
//							"value=" + data[i].empID + " readOnly='true'></td><td>"
//							+ data[i].name + " " + data[i].lastname
//							+ "</td><td><input name='amount'" +
//							" ></td></tr>";
//					$("#tableMoSaDetails tbody").append(result);
//				
//				}
//				var x = document.getElementById("vlivateAllEmps");
//				 x.setAttribute("value", "abc");
//			},
//			error : function(e) {
//				alert("Not Found Employees Or Employee Type");
//			}
//		});
//	}
//}
//
//function getRelatedDate() {
//
//	var y = document.getElementById("periodCode").value;	
//	$.ajax({
//		type: "GET",
//		url: "getDateReCode",
//		data: {"payPeriodID" : y},
//		success:function(data) {
//			document.getElementById("pYear1").value=data.startDate;	
//			document.getElementById("pMonth1").value=data.endDate;
//			getRelatedPayCodes2();
//			
//		},
//		error:function(e) {
//			alert("Dates Not Found");
//		}
//	});
//}
//
//function getPeriodIDReDates() {
//	var x = document.getElementById("pYear1").value;
//	var y = document.getElementById("pMonth1").value;
//	
//	$.ajax({
//		type: "GET",
//		url: "getPayCodeReDates",
//		data: { "Start_Date" : x, "End_Date" : y },
//		success:function(data) {
//			document.getElementById("periodCode").value= data.payPeriodID;
//			getPayCode2();
//			getRelatedPayCodes2();
//
//		},
//		error:function(e) {
//			alert("Pay Period not Found");
//		}
//	});
//}
//
//function getPayCode2() {
//	var y = document.getElementById("periodCode").value;	
//	$.ajax({
//		type: "GET",
//		url: "getPayCodeUsingPeriod",
//		data: {"payPeriodID" : y},
//		success:function(data) {
//			document.getElementById("pCode3").value= data.payCodeID;
//			
//		},
//		error:function(e) {
//			alert("Dates Not Found");
//		}
//	});
//}
//
//function getRelatedPayCodes2() {
//var a = document.getElementById("periodCode").value;
//	
//	var slctSubcat = $('#payCode'), option = "";
//	slctSubcat.empty();
//	
//	$.ajax({
//		type : "GET",
//		url : "getPayCodesRelatedPeriod",
//		data : {
//			"periodID" : a
//		},
//		success : function(data) {
//
//			var slctSubcat = $('#payCode'), option = "";
//			slctSubcat.empty();
//			selected_option = "<option value='' selected>--SELECT--</option>"
//			slctSubcat.append(selected_option);
//			
//			
//			
//			for (var i = 0; i < data.length; i++) {
//				option = option
//						+ "<option value='"+data[i].payCodeID + "'>"
//						+ data[i].payCode + "</option>";
//			}
//			
//			slctSubcat.append(option);
//
//		},
//		error : function(e) {
//			alert("Cant load pay codes");
//		}
//	});
//}
//
//function loadVariableTypes() {
//	$.ajax({
//		type: "GET",
//		url: "getAllVTypes",
//		success:function(data) {
//			var slctSubcat = $('#deductTypeCode'), option = "";
//			slctSubcat.empty();
//			selected_option = "<option value='' selected>Select Type</option>"
//			slctSubcat.append(selected_option);
//
//			for (var i = 0; i < data.length; i++) {
//				option = option
//						+ "<option value='"+data[i].deductTypeCode + "'>"
//						+ data[i].desc + "</option>";
//			}
//			slctSubcat.append(option);
//		},
//		error:function(e) {
//			alert("ID Does not Exists");
//		}
//	});
//}
//
//function getDefaultHeader() {
//	$.ajax({
//		type: "GET",
//		url: "loadRelatedHeader",
//		success:function(data) {
//			if(data.multipayperiod == 'Yes'
//				&& data.multipaycode == 'Yes') {
//				$("#sa").hide();
//				$("#year").hide();
//				$("#lMon").hide();
//				$("#lYea").hide();
//				$("#endDate").hide();
//				$("#startDate").hide();
//				$("#sd").hide();
//				$("#ed").hide();
//				document.getElementById("sa").disabled = true;
//				document.getElementById("year").disabled = true;
//				
//				$("#period2").hide();
//				document.getElementById("periodId").disabled = true;
//				
//				$("#code2").hide();
//				document.getElementById("pCode").disabled = true;
//				
//				$('#payCodeDiv3').hide();
//				document.getElementById("payCode3").disabled = true;
//				
//				$('#periodCodeDiv3').hide();
//				document.getElementById("periodCode3").disabled = true;
//				
//				$('#code3').hide();
//				document.getElementById("pCode3").disabled = true;
//				
//				
//			}else if(data.multipayperiod == 'No'
//				&& data.multipaycode == 'Yes') {
//				
//				$('#startDaveDiv1').hide();
//				document.getElementById("pYear1").disabled = true;
//				
//				$('#endDaveDiv1').hide();
//				document.getElementById("pMonth1").disabled = true;
//				
//				$('#periodCodeDiv').hide();
//				document.getElementById("periodCode").disabled = true;
//				
//				$('#code2').hide();
//				document.getElementById("pCode").disabled = true;
//				
//				$('#code3').hide();
//				document.getElementById("pCode3").disabled = true;
//				
//				$('#payCodeDiv3').hide();
//				document.getElementById("payCode3").disabled = true;
//				
//				$('#periodCodeDiv3').hide();
//				document.getElementById("periodCode3").disabled = true;
//				
//				
//			} else if(data.multipayperiod == 'Yes'
//				&& data.multipaycode == 'No') {
//				
//				$('#yearDiv').hide();
//				document.getElementById("year").disabled = true;
//				
//				$('#monthDiv').hide();
//				document.getElementById("sa").disabled = true;
//				
//				$('#startDateDiv2').hide();
//				document.getElementById("startDate").disabled = true;
//				
//				$('#abc').hide();
//				document.getElementById("endDate").disabled = true;
//				
//				$('#period2').hide();
//				document.getElementById("periodId").disabled = true;
//				
//				$('#payCodeDiv').hide();
//				document.getElementById("payCode").disabled = true;
//				
//				$('#code2').hide();
//				document.getElementById("pCode").disabled = true;
//				
//				$('#payCodeDiv3').hide();
//				document.getElementById("payCode3").disabled = true;
//				
//				$('#periodCodeDiv3').hide();
//				document.getElementById("periodCode3").disabled = true;
//				
//			} else {
//				$("#pYear").hide();
//				$("#pMonth").hide();
//				$("#periodCode").hide();
//				$("#lcode").hide();
//				
//				$("#startDaveDiv1").hide();
//				document.getElementById("pYear1").disabled = true;
//				
//				$("#endDaveDiv1").hide();
//				document.getElementById("pMonth1").disabled = true;
//				
//				$('#code3').hide();
//				document.getElementById("pCode3").disabled = true;
//				
//				$('#code2').hide();
//				document.getElementById("pCode").disabled = true;
//				
//				$('#payCodeDiv').hide();
//				document.getElementById("payCode").disabled = true;
//				
//				$('#period2').hide();
//				document.getElementById("periodId").disabled = true;
//
//			}
//		},
//		error:function(e) {
//			alert("Error Found Multi Pay Periouds");
//		}
//	});
//}
//
//function loadRePeriodCode() {
//	var x = document.getElementById("year").value;
//	var y = document.getElementById("sa").value;
//	
//	$.ajax({
//		type: "GET",
//		url: "getOnlyDates",
//		data: { "startDate" : x, "endDate" : y },
//		success:function(data) {
//			if(data.startDate != null  && data.endDate != null) {			
//			var d1 = new Date(data.startDate);
//			var d2 = new Date(data.endDate);
//			
//			var f1 = d1.getFullYear();
//			var f11 = d1.getMonth()+ 1;
//			var f111 = d1.getDate();
//			
//			var f2 = d2.getFullYear();
//			var f22 = d2.getMonth()+ 1;
//			var f222 = d2.getDate();
//			
//			var dFormat =  f1 + '-' + f11 + '-' + f111;
//			var dFormat2 = f2 + '-' + f22 + '-' + f222;
//			
//			document.getElementById("startDate").value= dFormat;
//			document.getElementById("endDate").value= dFormat2;
//			document.getElementById("periodId").value= data.payPeriodID;
//			document.getElementById("periodCode3").value= data.payPeriodID;
//
//			} 
//			else {
//				var x1 = new Date(x);
//				var y2 = new Date(y);
//				
//				var q1 = x1.getFullYear();
//				var q11 = y2.getMonth()+ 1;
//				var q111 = x1.getDate(data.startDate);
//				
//				var r1 = x1.getFullYear();
//				var r11 = y2.getMonth()+ 1;
//				var r111 = new Date(x1.getFullYear(), y2.getMonth() + 1, 0);
//				var r1111 = r111.getDate();
//				
//				var final = q1 + '-' + q11 + '-' + q111;
//				var final2 = r1 + '-' + r11 + '-' + r1111;
//						
//				document.getElementById("startDate").value= final;
//				document.getElementById("endDate").value= final2;
//				document.getElementById("periodId").value= data.payPeriodID;
//				document.getElementById("periodCode3").value= data.payPeriodID;
//				
//				
//			}
//			getRelatedPayCodes();
//			loadPayCode3();
//			
//		},
//		error:function(e) {
//			alert("Pay Period not Found");
//		}
//	});
//
//}
//
//function getRelatedPayCodes() {
//	var a = document.getElementById("periodId").value;
//	
//	var slctSubcat = $('#payCode'), option = "";
//	slctSubcat.empty();
//	
//	$.ajax({
//		type : "GET",
//		url : "getPayCodesRelatedPeriod",
//		data : {
//			"periodID" : a
//		},
//		success : function(data) {
//
//			var slctSubcat = $('#payCode'), option = "";
//			slctSubcat.empty();
//			selected_option = "<option value='' selected>--SELECT--</option>"
//			slctSubcat.append(selected_option);
//			
//			
//			
//			for (var i = 0; i < data.length; i++) {
//				option = option
//						+ "<option value='"+data[i].payCodeID + "'>"
//						+ data[i].payCode + "</option>";
//			}
//			
//			slctSubcat.append(option);
//
//		},
//		error : function(e) {
//			alert("Cant load pay codes");
//		}
//	});
//}
//
//function loadPayCode3() {
//	var y = document.getElementById("periodCode3").value;	
//	$.ajax({
//		type: "GET",
//		url: "getPayCodeUsingPeriod",
//		data: {"payPeriodID" : y},
//		success:function(data) {
//			document.getElementById("payCode3").value= data.payCodeID;
//			
//		},
//		error:function(e) {
//			alert("Dates Not Found");
//		}
//	});
//}














