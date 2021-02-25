function getDataRelatedEmployee(e) {
	$.ajax({
		type : 'GET',
		url : "empDataRelatedEmployee",
		data : { "empID" : e },
		success : function(data) {
			$("#detailsTbl tbody").empty();
			for (var i = 0; i < data.length; i++) {				
				var result = "<tr>" 
							+ "<td>"+ data[i][1] +"</td>"
							+ "<td>"+ data[i][2] +"</td>" 
							+ "<td>"+ data[i][3] +"</td>" 
							+ "<td>"+ data[i][4] +"</td>" 
							+ "<td>"+ data[i][5] +"</td></tr>";
				$("#detailsTbl tbody").append(result);
			}
		},
		error : function() {
			alert("Employee Data Not Found!");
		}

	});
}

function getAllEmpHeaderData() {
	var a = document.getElementById("addAllAllowance").checked;
	if(a == true) {
		$.ajax({
			type : 'GET',
			url : "allEmpDataRelatedBodyData",
			success : function(data) {
				$("#detailsTbl tbody").empty();
				for (var i = 0; i < data.length; i++) {				
					var result = "<tr>" 
								+ "<td>"+ data[i][1] +"</td>"
								+ "<td>"+ data[i][2] +"</td>" 
								+ "<td>"+ data[i][3] +"</td>" 
								+ "<td>"+ data[i][4] +"</td>" 
								+ "<td>"+ data[i][5] +"</td></tr>";
					$("#detailsTbl tbody").append(result);
				}
			},
			error : function() {
				alert("Error Found All Employee Details!");
			}
		});
	} else if(a == false) {
		$("#detailsTbl tbody").empty();
	} else {
		alert('function error');
	}
	
}

function loadTableRelatedDepartment(e) {
	$.ajax({
		type : 'GET',
		url : "GetDataToFTDRRelatedDepartment",
		data : { "depID" : e },
		success : function(data) {
			$("#detailsTbl tbody").empty();
			for (var i = 0; i < data.length; i++) {				
				var result = "<tr>" 
							+ "<td>"+ data[i][1] +"</td>"
							+ "<td>"+ data[i][2] +"</td>" 
							+ "<td>"+ data[i][3] +"</td>" 
							+ "<td>"+ data[i][4] +"</td>" 
							+ "<td>"+ data[i][5] +"</td></tr>";
				$("#detailsTbl tbody").append(result);
			}
		},
		error : function() {
			alert("Error Found All Employee Details Related Department!");
		}

	});
}

function loadTableAllDepartments() {
	var a = document.getElementById("addAllDepartments").checked;
	if(a == true) {
		$.ajax({
			type : 'GET',
			url : "getDataRelatedAllDepartments",
			success : function(data) {
				$("#detailsTbl tbody").empty();
				for (var i = 0; i < data.length; i++) {				
					var result = "<tr>" 
								+ "<td>"+ data[i][1] +"</td>"
								+ "<td>"+ data[i][2] +"</td>" 
								+ "<td>"+ data[i][3] +"</td>" 
								+ "<td>"+ data[i][4] +"</td>" 
								+ "<td>"+ data[i][5] +"</td></tr>";
					$("#detailsTbl tbody").append(result);
				}
			},
			error : function() {
				alert("Error Found All Employee Details Related All Departments!");
			}
		});
	} else if(a == false) {
		$("#detailsTbl tbody").empty();
	} else {
		alert('function error');
	}
	
}