function loadVariableTypes() {
	$.ajax({
		type : "GET",
		url : "getAllVTypes",
		success : function(data) {
			var slctSubcat = $('#deductTypeCode'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>--SELECT--</option>"
			slctSubcat.append(selected_option);

			for (var i = 0; i < data.length; i++) {
				option = option + "<option value='" + data[i].deductTypeCode
						+ "'>" + data[i].desc + "</option>";
			}
			slctSubcat.append(option);
			divsInvisible();
		},
		error : function(e) {
			alert("ID Does not Exists");
		}
	});
}

function loadRePeriodCode2() {
	var x = document.getElementById("year").value;
	var y = document.getElementById("sa").value;

	$.ajax({
		type: "GET",
		url: "getOnlyDates",
		data: { "startDate" : x, "endDate" : y },
		success:function(data) {
			if(data.startDate != null  && data.endDate != null) {			
			var d1 = new Date(data.startDate);
			var d2 = new Date(data.endDate);
			
			var f1 = d1.getFullYear();
			var f11 = d1.getMonth()+ 1;
			var f111 = d1.getDate();
			
			var f2 = d2.getFullYear();
			var f22 = d2.getMonth()+ 1;
			var f222 = d2.getDate();
			
			var dFormat =  f1 + '-' + f11 + '-' + f111;
			var dFormat2 = f2 + '-' + f22 + '-' + f222;
			
			document.getElementById("pYear").value= dFormat;
			document.getElementById("pMonth").value= dFormat2;
			document.getElementById("periodCode").value= data.payPeriodID;
			} 
			else {
				var x1 = new Date(x);
				var y2 = new Date(y);
				
				var q1 = x1.getFullYear();
				var q11 = y2.getMonth()+ 1;
				var q111 = x1.getDate(data.startDate);
				
				var r1 = x1.getFullYear();
				var r11 = y2.getMonth()+ 1;
				var r111 = new Date(x1.getFullYear(), y2.getMonth() + 1, 0);
				var r1111 = r111.getDate();
				
				var final = q1 + '-' + q11 + '-' + q111;
				var final2 = r1 + '-' + r11 + '-' + r1111;
						
				document.getElementById("pYear").value= final;
				document.getElementById("pMonth").value= final2;
				document.getElementById("periodCode").value= data.payPeriodID;			
				
			}
			getRelatedPayCodes2();
			divsVisible();
		},
		error:function(e) {
			alert("Pay Period not Found");
		}
	});
}

function getRelatedPayCodes2() {
	var y = document.getElementById("periodCode").value;	
	$.ajax({
		type: "GET",
		url: "getPayCodeUsingPeriod",
		data: {"payPeriodID" : y},
		success:function(data) {
			document.getElementById("pCode3").value= data.payCodeID;
			document.getElementById("PayCodeVal").value= data.payCode;
			
		},
		error:function(e) {
			alert("Dates Not Found");
		}
	});
}

function loadAddDed() {
	var y = document.getElementById("deductTypeCode").value;
	$.ajax({
		type : "GET",
		url : "loadAddDedType",
		data : {
			"deductTypeCode" : y
		},
		success : function(data) {
			$("#addDeType").empty();
			var a = document.getElementById("addDeType");
			a.setAttribute("value", data.addDeType);
			alloTypeDivVisible();
		},
		error : function(e) {
			alert("Not Found Addition or Deduction Type");
		}
	});
}

function loadRelatedDep() {
var y = document.getElementById("depID").value;
var z = document.getElementById("deductTypeCode").value;
$.ajax({
	type : "GET",
	url : "loadReDep",
	data : {
		"dep" : y,
		"deductTypeCode" : z
	},
	success : function(data) {
			
		$("#tableMoSaDetails tbody").empty();
		for (var i = 0; i < data.length; i++) {
			var result = "<tr><td><input type='checkbox' id='cb1' onchange='setValues()'" +
					" name='cb1' value='inactive'>" +
					"</td>" +
					"<td><input name='monthDePk.empID.empID' id='empidTable'" +
					"value=" + data[i].empdetailPK.empID.empID
					+ " readOnly></td><td>" + data[i].empdetailPK.empID.name + ""
					+ data[i].empdetailPK.empID.lastname + "</td><td><input name='amount' class='amount'" +
					 + " ></td></tr>";
							
				$("#tableMoSaDetails tbody").append(result);
		}
	},
	error : function(e) {
		alert("Not Found Employees Or Departments");
	}
});
}

function loadRelatedLoc() {
var z = document.getElementById("deductTypeCode").value;
var y = document.getElementById("loid").value;
$
		.ajax({
			type : "GET",
			url : "loadEmpRelatedLocation",
			data : {
				"loid" : y,
				"deductTypeCode" : z
			},
			success : function(data) {
				$("#tableMoSaDetails tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td><input type='checkbox' onchange='setValues()'" +
							" id='cb1' name='cb1' value='inactive'>" +
							"</td><td><input name='monthDePk.empID.empID'"
							+ "value=" + data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input name='amount'" +
							+ " ></td></tr>";
					$("#tableMoSaDetails tbody").append(result);

				}
			},
			error : function(e) {
				alert("Not Found Employees Or Location");
			}
		});
}

function loadRelatedCat() {
var z = document.getElementById("deductTypeCode").value;
var y = document.getElementById("catgoryID").value;
$
		.ajax({
			type : "GET",
			url : "loadEmpRelatedCat",
			data : {
				"catgoryID" : y,
				"deductTypeCode" : z
			},
			success : function(data) {
				$("#tableMoSaDetails tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
							"</td><td><input name='monthDePk.empID.empID' value="
							+ data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input name='amount'" +
							+ " ></td></tr>";
					$("#tableMoSaDetails tbody").append(result);

					var result2 = "<input type='hidden' name='monthDePk.empID.empID' " +
					"value=" + data[i].empdetailPK.empID.empID + ">";
					$("#tEmpID").append(result2);
				}
			},
			error : function(e) {
				alert("Not Found Employees Or Employee Type");
			}
		});
}

function loadRelatedType() {
var y = document.getElementById("tid").value;
var z = document.getElementById("deductTypeCode").value;
$
		.ajax({
			type : "GET",
			url : "loadEmpRelatedType",
			data : {
				"tid" : y,
				"deductTypeCode" : z
			},
			success : function(data) {
				$("#tableMoSaDetails tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
							"</td><td><input name='monthDePk.empID.empID' value="
							+ data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input name='amount'" +
							+ " ></td></tr>";
					$("#tableMoSaDetails tbody").append(result);
					
				}
			},
			error : function(e) {
				alert("Not Found Employees Or Employee Type");
			}
		});
}

function loadAllEmps() {
if (document.getElementById("inlineRadio5").checked) {
	$.ajax({
		type : "GET",
		url : "loadAllEmployeesToGrid",
		success : function(data) {
			$("#tableMoSaDetails tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
						"</td><td><input name='monthDePk.empID.empID' " +
						"value=" + data[i].empID + " readOnly='true'></td><td>"
						+ data[i].name + " " + data[i].lastname
						+ "</td><td><input name='amount'" +
						" ></td></tr>";
				$("#tableMoSaDetails tbody").append(result);
			
			}
			var x = document.getElementById("vlivateAllEmps");
			 x.setAttribute("value", "abc");
		},
		error : function(e) {
			alert("Not Found Employees Or Employee Type");
		}
	});
}
}

function loadEmpToTable() {
var y = document.getElementById("empID").value;
$.ajax({
	type : "GET",
	url : "loadEmpTable",
	data : {
		"empID" : y
	},
	success : function(data) {

		$("#tableMoSaDetails tbody").empty();
		for (var i = 0; i < data.length; i++) {
			var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
					"</td><td><input name='empidTable' id='empidTable'" +
					"value=" + data[i].empID
					+ " readOnly></td><td>" + data[i].name + " " + data[i].lastname
					+ "</td><td><input name='amount'" +
					" ></td></tr>";
			$("#tableMoSaDetails tbody").append(result);
			
			 $("#tEmpID1").empty();
			 var a = document.getElementById("tEmpID1");
			 a.setAttribute("value", data[i].empID);

		}
	},
	error : function(e) {
		alert("Not Found Employees Or Departments");
	}
});
}

function divsInvisible() {
	$('#startDateDiv').hide();
	$('#endDateDiv').hide();
	$('#payCodeValDiv').hide();
	$('#alloTypeDiv').hide();
}

function divsVisible() {
	$('#startDateDiv').slideDown();
	$('#endDateDiv').slideDown();
	$('#payCodeValDiv').slideDown();
}

function alloTypeDivVisible() {
	$('#alloTypeDiv').slideDown();
}












