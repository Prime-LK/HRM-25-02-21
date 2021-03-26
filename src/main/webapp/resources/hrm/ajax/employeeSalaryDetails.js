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
					       + 'onChange="loadRelatedDepEmployee();visibleDataTable01()">'
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
	} else if(sltVal === "category") {
		$.ajax({
			type: "GET",
			url: "categories",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Category</label>'
					       + '<select class="form-control" name="cat" id="catgoryID"' 
					       + 'onchange="loadRelatedcategoryEmployee();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
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
	} else if(sltVal === "type") {
		$.ajax({
			type: "GET",
			url: "types",
			success:function(data) {
				$('#loadSepDiv').empty();
				var result = '<label>Type</label>'
					       + '<select class="form-control" name="type" id="tid"' 
					       + 'onchange="loadRelatedEmployeebasedOnTypes();visibleDataTable01()">'
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
					       + '<select class="form-control" name="emp" id="loginEmp"' 
					       + 'onchange="loadEmployee();visibleDataTable01()">'
						   + '<option value="" selected="true">--SELECT--</option>'
						   + '</select>';
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

			},
			error:function(e) {
				alert("Error Found Loading Employee Data");
			}
		});
	} else {
		loadAllEmployee();
		visibleDataTable01();
	}
}

//load data according to empID and 
function loadRelatedData() {
	var y = document.getElementById("empID").value;
	$.ajax({
		type: "GET",
		url: "loadrelavantsalaryData",
		data: {"empID": y},
		success:function(data) {
			$("#tableSalaryDetails tbody").empty();

			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" 
					  		 + data[i].empdetailPK.empID.empID + "</td><td>" 
					  		 + data[i].empdetailPK.payAddeductTypes.desc + "</td><td>" 
					  		 + data[i].amount + "</td><td>" 
					  		 + data[i].added_Date + "</td><td>" 
					  		 + data[i].effective_Date + "</td><td>"
					  		 + data[i].added_User + "</td><td>"
					  		 + data[i].inactive_From+ "</td><td>"
					  		 + data[i].inactive_User + "</td><td>"
					  		 + data[i].isActive + "</td><td><a href=updateSalaryDetails?empID="
					  		 + data[i].empdetailPK.empID.empID + "&deductTypeCode=" +data[i].empdetailPK.payAddeductTypes.deductTypeCode+
					  		 "><img src='resources/img/edit.png' width='25px' height='25px'></a></td><td>"
					  		 "</td></tr>";
				$("#tableSalaryDetails tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees");
		}
	});
}

//load employee based on department
function loadRelatedDepEmployee() {
	var y = document.getElementById("depID").value;
	$.ajax({
		type: "GET",
		url: "loadEmprelatedDepartment",
		data: {"depID": y},
		success:function(data) {
			$("#filterEmp tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input name='empdetailPK.empID.empID' id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " readOnly></td><td>" 
			  		 + data[i].detailsPK.empID.name + " "+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";			  		
				$("#filterEmp tbody").append(result);			
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}


//load employee based on location
function loadRelatedLocationEmployee() {
	var y = document.getElementById("loid").value;
	$.ajax({
		type: "GET",
		url: "loadEmprelatedLocation",
		data: {"loid": y},
		success:function(data) {
			
			$("#filterEmp tbody").empty();

			for (var i = 0; i < data.length; i++) {
				  
				var result = "<tr><td><input name='empdetailPK.empID.empID' id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " ></td><td>" 
			  		 + data[i].detailsPK.empID.name + " "+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";
				
				$("#filterEmp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}

//load employee based on employee category
function loadRelatedcategoryEmployee() {

	var y = document.getElementById("catgoryID").value;

	$.ajax({
		type: "GET",
		url: "loadrelatedEmpBasedOnCatgory",
		data: {"catgoryID": y},
		success:function(data) {
			
			$("#filterEmp tbody").empty();

			for (var i = 0; i < data.length; i++) {
				  
				var result = "<tr><td><input name='empdetailPK.empID.empID'  id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " ></td><td>" 
			  		 + data[i].detailsPK.empID.name + ""+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";
			  	
			  		
				$("#filterEmp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}


//load employee based on employee types
function loadRelatedEmployeebasedOnTypes() {

	var y = document.getElementById("tid").value;

	$.ajax({
		type: "GET",
		url: "loadrelatedEmpBasedOnType",
		data: {"tid": y},
		success:function(data) {
			
			$("#filterEmp tbody").empty();

			for (var i = 0; i < data.length; i++) {
				  
				var result = "<tr><td><input name='empdetailPK.empID.empID' id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " readOnly></td><td>" 
			  		 + data[i].detailsPK.empID.name + " "+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";
			  	
			  		
				$("#filterEmp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}

//load employee to employee salary details
function loadEmployee() {
	const y = document.getElementById("loginEmp").value;
	$.ajax({
		type: "GET",
		url: "loadrelatedEmployee",
		data: {"empID": y},
		success:function(data) {
			$("#filterEmp tbody").empty();
			for (var i = 0; i < data.length; i++) {
				  
				var result = "<tr><td><input name='empdetailPK.empID.empID' id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " readOnly /></td><td>" 
			  		 + data[i].detailsPK.empID.name + " "+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";			  		
				$("#filterEmp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Employee Not Found");
		}
	});
}

//load all employee to employee salary details
function loadAllEmployee() {
	$.ajax({
		type: "GET",
		url: "loadallEmp",
		//data: {"tid": y},
		success:function(data) {
			$("#filterEmp tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input name='empdetailPK.empID.empID' id='tableEmpId' value =" 
			  		 + data[i].detailsPK.empID.empID + " readOnly></td><td>" 
			  		 + data[i].detailsPK.empID.name + " "+data[i].detailsPK.empID.lastname+"</td><td>" 
			  		 + data[i].empType.type + "</td><td>" 
			  		 + data[i].category.category + "</td></tr>";
			  	
				$("#filterEmp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}

function loadFixedTypeOnly() {
	$.ajax({
		type: "GET",
		url: "loadFixedTypeOnly",
		success:function(data) {
			var slctSubcat = $('#addDedctTypeID'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>Select Type</option>"
			slctSubcat.append(selected_option);

			for (var i = 0; i < data.length; i++) {
				option = option
						+ "<option value='"+data[i].deductTypeCode + "'>"
						+ data[i].desc + "</option>";
			}
			slctSubcat.append(option);
		},
		error:function(e) {
			alert("Deduct Type Not Found");
		}
	});
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