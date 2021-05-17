function loadRelatedSelect() {
	var sltVal = $('#sepSelect').val();
	if(sltVal == "department") {
		$.ajax({
			type: "GET",
			url: "departments",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<form action="generatePaySlipRelatedDepartment" method="POST">'
							 + '<div class="form-group">'
							 + '<label>Department</label> <select class="form-control" name="depID" id="depID">'
							 + '<option value="" selected>--SELECT--</option>'
							 + '</select>'
							 + '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
							 + '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
							 + '</div></form>';
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
					       + 'onChange="loadRelatedLocationEmployee();visibleDataTable01()">'
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
	} else if(sltVal === "allEmp") {
		$.ajax({
			type: "GET",
			url: "categories",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<form action="generateAllEmployeesPaySlips" method="POST">'
							+ '<div class="form-group">'
							+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
							+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
							+ '</div></form>';
				$('#loadSepDiv').append(result);
				
				var slctSubcat = $('#catgoryID'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);
				
				for (var i = 0; i < data.length; i++) {
					option = option
							+ "<option value='"+data[i].catID + "'>"
							+ data[i].category + "</option>";
				}
				slctSubcat.append(option);

			},
			error:function(e) {
				alert("Error Found Loading Category Data");
			}
		});
	} else if(sltVal === "paySheet") {
		$.ajax({
			type: "GET",
			url: "types",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<form action="generateEmpAllAllowanceReport" method="POST">'
							+ '<div class="form-group">'
							+ '<button type="submit" id="submitBtn" class="btn btn-success mt-4">'
							+ '<i class="fa fa-print" aria-hidden="true"></i> Generate Report</button>'
							+ '</div></form>';
				$('#loadSepDiv').append(result);
			},
			error:function(e) {
				alert("Error Found Load Details of Pay Sheet");
			}
		});
	} else if(sltVal === "employee") {
		$.ajax({
			type: "GET",
			url: "loadAllEmpInEmpDetails",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<form action="sampleReport" method="POST">'
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
					option = option
							+ "<option value='"+data[i].detailsPK.empID.empID + "'>"
							+ data[i].detailsPK.empID.name +" "+ data[i].detailsPK.empID.lastname +"</option>";
				}
				slctSubcat.append(option);
				slideDown();
			},
			error:function(e) {
				alert("Error Found Loading Employee Data");
			}
		});
	} else {
	}
}

function slideDown() {
	$('#loadSepDiv').slideDown();
}