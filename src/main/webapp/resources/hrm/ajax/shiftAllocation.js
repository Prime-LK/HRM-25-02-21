function loadEmployeeIdByDepartmentId() {

	var selectDepartment = document.getElementById("selectDepartment").value;
	if (selectDepartment == "") {
		var selectEmployee = $("#selectEmployeeId"), option = "";
		selectEmployee.empty();
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "loadEmployeeIdByDepartmentId",
					data : {
						"depID" : selectDepartment
					},
					success : function(data) {
						if (data.length == 0) {
							swal(
									"No data is available for the selected parameters!",
									"", {
										icon : "info",
										buttons : {
											confirm : {
												className : 'btn btn-primary'
											}
										},
									});
						}
						var selectEmployee = $("#selectEmployeeId"), option = "";
						selectEmployee.empty();
						selected_option = "<option value='' selected>- Select Employee -</option>";
						selectEmployee.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='"
									+ data[i].detailsPK.empID.empID + "'>"
									+ data[i].detailsPK.empID.name + " "
									+ data[i].detailsPK.empID.lastname
									+ "</option>";
						}
						selectEmployee.append(option);
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

function loadDepartmentName() {

	var selectDepartment = document.getElementById("selectDepartment").value;
	if (selectDepartment == "") {
		var departmentName = $("#depName");
		departmentName.empty();
		return;
	} else {

		$.ajax({
			type : 'GET',
			url : "loadDepartmentNameById",
			data : {
				"depID" : selectDepartment
			},
			success : function(data) {
				if (data.length == 0) {
					swal("No data is available for the selected parameters!",
							"", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
				}
				var departmentName = $("#depName");
				departmentName.empty();
				document.getElementById("depName").value = data.department;
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

function loadShiftById() {

	var selectShift = document.getElementById("shiftId").value;
	if (selectShift == "") {
		var selectStartTime = $("#startTime");
		selectStartTime.empty();
		var selectEndTime = $("#endTime");
		selectEndTime.empty();
		return;
	} else {

		$.ajax({
			type : 'GET',
			url : "loadShiftById",
			data : {
				"shiftId" : selectShift
			},
			success : function(data) {
				if (data.length == 0) {
					swal("No data is available for the selected parameters!",
							"", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
				}

				var selectStartTime = $("#startTime");
				selectStartTime.empty();
				var selectEndTime = $("#endTime");
				selectEndTime.empty();
				document.getElementById("startTime").value = data.startTime;
				document.getElementById("endTime").value = data.endTime;
				document.getElementById("duration").value = calculateDuration(
						data.startTime, data.endTime);

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

function calculateDuration(valuestart, valuestop) {

	// create date format
	var timeStart = new Date("01/01/2007 " + valuestart).getHours();
	var timeEnd = new Date("01/01/2007 " + valuestop).getHours();

	var hourDiff = timeEnd - timeStart;
	if (hourDiff < 0) {
		hourDiff = 24 + hourDiff;
	}

	var timeStart = new Date("01/01/1970 " + valuestart).getMinutes();
	var timeEnd = new Date("01/01/1970 " + valuestop).getMinutes();

	var minuteDiff = timeEnd - timeStart;
	if (minuteDiff < 0) {
		minuteDiff = 60 + minuteDiff;
		hourDiff = hourDiff - 1;
	}

	return hourDiff + " hrs : " + minuteDiff + " minutes";

}

function loadShiftsByDateRange() {

	var selectStartDate = document.getElementById("startDate").value;
	var selectEndDate = document.getElementById("endDate").value;
	var selectShift = document.getElementById("shiftId").value;

	if (selectStartDate == "" || selectEndDate == "" || selectShift == "") {
		return;
	} else {

		$.ajax({
			type : 'GET',
			url : "loadShiftsByDateRange",
			data : {
				"startDate" : selectStartDate,
				"endDate" : selectEndDate,
				"shiftId" : selectShift
			},
			success : function(data) {
				if (data.length == 0) {
					swal("No data is available for the selected parameters!",
							"", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
				}
				$("#dataTable tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td>" + data[i].date + "</td><td>"
							"</td><td>" + data[i].shift
							+ "</td><td>" + data[i].startTime + "</td><td>"
							+ data[i].endTime + "</td><td>"
							+ data[i].employee + "</td><td>" + data[i].department
							+ "</td></tr>";

					$("#dataTable tbody").append(result);

				}
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
