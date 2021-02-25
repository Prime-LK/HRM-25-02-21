function validateDropDown() {
	if (document.getElementById("inlineRadio1").checked) {
		document.getElementById("depID").disabled = false;
	} else if (document.getElementById("inlineRadio2").checked) {
		document.getElementById("loid").disabled = false;
	} else if (document.getElementById("inlineRadio3").checked) {
		document.getElementById("catgoryID").disabled = false;
	} else if (document.getElementById("inlineRadio4").checked) {
		document.getElementById("tid").disabled = false;
	} else if (document.getElementById("inlineRadio6").checked) {
		document.getElementById("empID").disabled = false;
	}

}

function validateSelectField() {
	document.getElementById("depID").disabled = true;
	document.getElementById("loid").disabled = true;
	document.getElementById("catgoryID").disabled = true;
	document.getElementById("tid").disabled = true;
	document.getElementById("empID").disabled = true;
}

function loadVariableTypes() {
	$.ajax({
		type : "GET",
		url : "getAllVTypes",
		success : function(data) {
			var slctSubcat = $('#deductTypeCode'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>Select Type</option>"
			slctSubcat.append(selected_option);

			for (var i = 0; i < data.length; i++) {
				option = option + "<option value='" + data[i].deductTypeCode
						+ "'>" + data[i].desc + "</option>";
			}
			slctSubcat.append(option);
		},
		error : function(e) {
			alert("ID Does not Exists");
		}
	});
}

function getPeriodIDReDates() {
	var x = document.getElementById("pYear").value;
	var y = document.getElementById("pMonth").value;

	$.ajax({
		type : "GET",
		url : "getPayCodeReDates",
		data : {
			"Start_Date" : x,
			"End_Date" : y
		},
		success : function(data) {
			document.getElementById("periodCode").value = data.payPeriodID;
			getRelatedPayCodes2();

		},
		error : function(e) {
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
					"</td><td></td><td><input name='empidTable' id='empidTable'" +
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














