function loadRelatedSelect() {
	var sltVal = $('#sepSelect').val();
	if(sltVal == "department") {
		$.ajax({
			type: "GET",
			url: "departments",
			success:function(data) { 
				$('#loadSepDiv').empty();
				var result = '<label>Department</label>'
					       + '<select class="form-control" name="dep" id="depID"' 
					       + 'onChange="loadRelatedDep();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#depID'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].depID + "'>"
							+ data[i].department + "</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Department Data");
			}
		});

	} else if(sltVal === "location") {
		$.ajax({
			type: "GET",
			url: "loadlocations",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Location</label>'
					       + '<select class="form-control" name="lo" id="loid"'
					       + 'onChange="loadRelatedLoc();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#loid'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].loid + "'>"
							+ data[i].location + "</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Location Data");
			}
		});
	} else if(sltVal === "category") {
		$.ajax({
			type: "GET",
			url: "categories",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Category</label>'
					       + '<select class="form-control" name="cat" id="categoryID"' 
					       + 'onchange="loadRelatedCat();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#categoryID'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].catgoryID + "'>"
							+ data[i].category + "</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Category Data");
			}
		});
	} else if(sltVal === "type") {
		$.ajax({
			type: "GET",
			url: "types",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Type</label>'
					       + '<select class="form-control" name="type" id="tid"' 
					       + 'onchange="loadRelatedType();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#tid'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].tid + "'>"
							+ data[i].type + "</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Type Data");
			}
		});
	} else if(sltVal === "employee") {
		$.ajax({
			type: "GET",
			url: "loadAllEmpInEmpDetails",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Employee</label>'
					       + '<select class="form-control" name="emp" id="empID"' 
					       + 'onchange="loadEmpToTable();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#empID'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].detailsPK.empID.empID + "'>"
							+ data[i].detailsPK.empID.name +" "+ data[i].detailsPK.empID.lastname +"</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Employee Data");
			}
		});
	} else {
		loadAllEmps();
		visibleDataTable01();
		$('#loadSepDiv').empty();
	}
}

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
			document.getElementById("periodCodeVal").value= data.desc;
			getRelatedPayCodes2();
			divsVisible();

		},
		error : function(e) {
			alert("Pay Period not Found");
		}
	});
}

function getRelatedPayCodes2() {
	var a = document.getElementById("periodCode").value;

	var slctSubcat = $('#payCode'), option = "";
	slctSubcat.empty();

	$.ajax({
		type : "GET",
		url : "getPayCodesRelatedPeriod",
		data : {
			"periodID" : a
		},
		success : function(data) {

			var slctSubcat = $('#payCode'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>--SELECT--</option>"
			slctSubcat.append(selected_option);

			for (var i = 0; i < data.length; i++) {
				option = option + "<option value='" + data[i].payCodeID + "'>"
						+ data[i].payCode + "</option>";
			}

			slctSubcat.append(option);
		},
		error : function(e) {
			alert("Cant load pay codes");
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
					+ data[i].empdetailPK.empID.lastname + "</td><td>"
					+ "<input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
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
							"</td><td><input name='monthDePk.empID.empID' id='empidTable'"
							+ "value=" + data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
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
var y = document.getElementById("categoryID").value;
$
		.ajax({
			type : "GET",
			url : "loadEmpRelatedCat",
			data : {
				"categoryID" : y,
				"deductTypeCode" : z
			},
			success : function(data) {
				$("#tableMoSaDetails tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td><input type='checkbox' id='cb1' name='cb1'>" +
							"</td><td><input name='monthDePk.empID.empID' id='empidTable' value="
							+ data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
							+ " ></td></tr>";
					$("#tableMoSaDetails tbody").append(result);

					var result2 = "<input type='text' name='monthDePk.empID.empID' " +
					"value=" + data[i].empdetailPK.empID.empID + ">";
					$("#tEmpID").append(result2);
				}
			},
			error : function(e) {
				alert("Not Found Employees Or Employee Type");
			}
		});
}
//onclick='activePerson() getEmpMonthSalaryDetailsPage01
function activePerson() {
	$("#empidTable").prop("disabled", (_, val) => !val);
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
							"</td><td><input name='monthDePk.empID.empID' id='empidTable' value="
							+ data[i].empdetailPK.empID.empID
							+ " ></td><td>"
							+ data[i].empdetailPK.empID.name
							+ " "
							+ data[i].empdetailPK.empID.lastname
							+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
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
	$.ajax({
		type : "GET",
		url : "loadAllEmployeesToGrid",
		success : function(data) {
			$("#tableMoSaDetails tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
						"</td><td><input name='monthDePk.empID.empID' id='empidTable'" +
						"value=" + data[i].empID + " readOnly='true'></td><td>"
						+ data[i].name + " " + data[i].lastname
						+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
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
					+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
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
	$('#payPeriodValDiv').hide();
	$('#alloTypeDiv').hide();
}

function divsVisible() {
	$('#payPeriodValDiv').slideDown();
}

function alloTypeDivVisible() {
	$('#alloTypeDiv').slideDown();
}

function invisibleDataTable01() {
	$('#dataTableBasic').hide();
}

function visibleDataTable01() {
	$('#dataTableBasic').slideDown();
}

function slideUpDatable01() {
	$('#dataTableBasic').slideUp();
	$('#loadSepDiv').empty();
}

