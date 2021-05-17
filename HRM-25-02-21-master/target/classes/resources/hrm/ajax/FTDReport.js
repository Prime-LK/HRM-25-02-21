function getDataRelatedEmployee(e) {
	$.ajax({
		type : 'GET',
		url : "empDataRelatedEmployee",
		data : {
			"empID" : e
		},
		success : function(data) {
			$("#detailsTbl tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" + "<td>" + data[i][1] + "</td>" + "<td>"
						+ data[i][2] + "</td>" + "<td>" + data[i][3] + "</td>"
						+ "<td>" + data[i][4] + "</td>" + "<td>" + data[i][5]
						+ "</td></tr>";
				$("#detailsTbl tbody").append(result);
			}
			visibleFields();
		},
		error : function() {
			alert("Employee Data Not Found!");
		}
	});
}

function getAllEmpHeaderData() {
    var a = document.getElementById("empsCheckBox").checked;
	if (a == true) {
		$.ajax({
			type : 'GET',
			url : "allEmpDataRelatedBodyData",
			success : function(data) {
				$("#detailsTbl tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr>" + "<td>" + data[i][1] + "</td>"
							+ "<td>" + data[i][3] + "</td>" + "<td>"
							+ data[i][4] + "</td>" + "<td>" + data[i][5]
							+ "</td>" + "<td>" + data[i][6] + "</td></tr>";
					$("#detailsTbl tbody").append(result);
				}
				visibleFields();
			},
			error : function() {
				alert("Error Found All Employee Details!");
			}
		});
	} else if (a == false) {
		$("#detailsTbl tbody").empty();
		gridSlideUp();
	} else {
		alert('function error');
	}
}

function loadTableRelatedDepartment(e) {
	$.ajax({
		type : 'GET',
		url : "GetDataToFTDRRelatedDepartment",
		data : {
			"depID" : e
		},
		success : function(data) {
			console.log(e);
			$("#detailsTbl tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" + "<td>" + data[i][1] + "</td>" + "<td>"
						+ data[i][3] + "</td>" + "<td>" + data[i][4] + "</td>"
						+ "<td>" + data[i][5] + "</td>" + "<td>" + data[i][6]
						+ "</td></tr>";
				$("#detailsTbl tbody").append(result);
			}
			visibleFields();
		},
		error : function() {
			alert("Error Found All Employee Details Related Department!");
		}

	});
}

function loadTableAllDepartments() {
	 var a = document.getElementById("depsCheckBox").checked;
	if (a == true) {
		$
				.ajax({
					type : 'GET',
					url : "getDataRelatedAllDepartments",
					success : function(data) {
						$("#detailsTbl tbody").empty();
						for (var i = 0; i < data.length; i++) {
							var result = "<tr>" + "<td>" + data[i][1] + "</td>"
									+ "<td>" + data[i][3] + "</td>" + "<td>"
									+ data[i][4] + "</td>" + "<td>"
									+ data[i][5] + "</td>" + "<td>"
									+ data[i][6] + "</td></tr>";
							$("#detailsTbl tbody").append(result);
						}
						visibleFields();
					},
					error : function() {
						alert("Error Found All Employee Details Related All Departments!");
					}
				});
	} else if (a == false) {
		$("#detailsTbl tbody").empty();
		gridSlideUp();
	} else {
		alert('function error');
	}
}
// dynamic select for reports
function loadRelatedSelect() {
	var sltVal = $('#sepSelect').val();
	if (sltVal == "allDeps") {
		$.ajax({
					type : "GET",
					url : "departments",
					success : function(data) {
						$('#loadSepDiv').empty();
						var result = '<form action="FTDRDepartments" method="POST">'
								+ '<div class="form-group">'
								+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
								+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
								+ '</div></form>';
						$('#loadSepDiv').append(result);
					},
					error : function(e) {
						alert("Error Found Loading Departments Data");
					}
				});

	} else if (sltVal === "dep") {
		$.ajax({
					type : "GET",
					url : "departments",
					success : function(data) {
						$('#loadSepDiv').empty();
						var result = '<form action="FTDRDepartment" method="POST">'
								+ '<div class="form-group">'
								+ '<label>Department</label> <select class="form-control" name="depid" id="depid">'
								+ '<option value="" selected>--SELECT--</option>'
								+ '</select>'
								+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
								+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
								+ '</div></form>';
						$('#loadSepDiv').append(result);

						var slctSubcat = $('#depid'), option = "";
						slctSubcat.empty();
						selected_option = "<option value='' selected>--SELECT--</option>"
						slctSubcat.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='" + data[i].depID
									+ "'>" + data[i].department + "</option>";
						}
						slctSubcat.append(option);

					},
					error : function(e) {
						alert("Error Found Loading Department Data");
					}
				});
	} else if (sltVal === "allEmps") {
		$
				.ajax({
					type : "GET",
					url : "categories",
					success : function(data) {
						$('#loadSepDiv').empty();
						var result = '<form action="FTDREmployees" method="POST">'
								+ '<div class="form-group">'
								+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
								+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
								+ '</div></form>';
						$('#loadSepDiv').append(result);

					},
					error : function(e) {
						alert("Error Found Loading All Employee Data");
					}
				});
	} else if (sltVal === "emp") {
		$
				.ajax({
					type : "GET",
					url : "loadAllEmpInEmpDetails",
					success : function(data) {
						$('#loadSepDiv').empty();
						var result = '<form action="FTDREmployee" method="POST">'
								+ '<div class="form-group">'
								+ '<label>Employee</label> <select class="form-control" name="empID" id="loginEmp">'
								+ '<option value="" selected>--SELECT--</option>'
								+ '</select>'
								+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
								+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
								+ '</div></form>';
						$('#loadSepDiv').append(result);

						var slctSubcat = $('#loginEmp'), option = "";
						slctSubcat.empty();
						selected_option = "<option value='' selected>--SELECT--</option>"
						slctSubcat.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='"
									+ data[i].detailsPK.empID.empID + "'>"
									+ data[i].detailsPK.empID.name + " "
									+ data[i].detailsPK.empID.lastname
									+ "</option>";
						}
						slctSubcat.append(option);
						visibleFields();
					},
					error : function(e) {
						alert("Error Found Loading Employee Data");
					}
				});
	} 
}

function inVisibleFields() {
	$('#detailsTableDiv').hide();
}

function visibleFields() {
	$('#detailsTableDiv').slideDown();
}

function gridSlideUp() {
	$('#detailsTableDiv').slideUp();
}