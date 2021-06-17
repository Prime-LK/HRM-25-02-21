function getDetails() {
	var x = document.getElementById("empID").value;
	$.ajax({
		type : "GET",
		url : "getRelatedTableData",
		data : {
			"empID" : x
		},
		success : function(data) {
			var result = "<ul class='list-group list-group-flush'> "
					+ "<li class='list-group-item' id='epfNoData'>" + data.epfNo + "</li>"
					+ "<li class='list-group-item' id='epfNoData'>" + data.joinedDate + "</li>"
					+ "<li class='list-group-item' id='epfNoData'>" + data.status + "</li>"
					+ "<li class='list-group-item' id='epfNoData'>" + data.basicSalary + "</li>"
					+ "<li class='list-group-item' id='epfNoData'>" + data.designation.designation + "</li>"
					+ "</ul>";
			$("#card001Details").append(result);
			
			var result2 = "<ul class='list-group list-group-flush'> "
				+ "<li class='list-group-item' id='epfNoData'>" + data.category.category + "</li>"
				+ "<li class='list-group-item' id='epfNoData'>" + data.empType.type + "</li>"
				+ "<li class='list-group-item' id='epfNoData'>" + data.department.department + "</li>"
				+ "<li class='list-group-item' id='epfNoData'>" + data.salaryGrade.grade + "</li>"
				+ "<li class='list-group-item' id='epfNoData'>" + data.salaryRange.range + "</li>"
				+ "</ul>";
		    $("#card002Details").append(result2);
		
		   var result3 = "<ul class='list-group list-group-flush'> "
			+ "<li class='list-group-item' id='epfNoData'>" + data.reporting + "</li>"
			+ "<li class='list-group-item' id='epfNoData'>" + data.jobProfile.profile + "</li>"
			+ "<li class='list-group-item' id='epfNoData'>" + data.location.location + "</li>"
			+ "<li class='list-group-item' id='epfNoData'>" + data.resignDate + "</li>"
			+ "</ul>";
	     $("#card003Details").append(result3);
		},
		error : function(e) {
			// alert("Employee ID Not Found " + x);
		}
	});
}

function loadSalaryRangesByGradeAndCompany() {

	var gradeId = document.getElementById("gradeId").value;
	if (gradeId == "") {
		var selectSalaryRange = $("#rangeId"), option = "";
		selectSalaryRange.empty();
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "getAllSalaryRangeByGradeAndCompany",
					data : {
						"gradeId" : gradeId
					},
					success : function(data) {
						if (data.length == 0) {
							swal("No data is available for the selected parameters!", "", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
						}
						var selectSalaryRange = $("#rangeId"), option = "";
						selectSalaryRange.empty();
						selected_option = "<option value='' selected>--SELECT--</option>";
						selectSalaryRange.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='"
									+ data[i].salaryRangeID + "'>"
									+ data[i].range
									+ "</option>";
						}
						selectSalaryRange.append(option);
					},
					error : function() {
						swal("Error!", "", {
							icon : "error",
							buttons : {
								confirm : {
									className : 'btn btn-danger'
								}
							},
						});
					}

				});
	}

}