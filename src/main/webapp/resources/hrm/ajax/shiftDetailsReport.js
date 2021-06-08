function loadShiftDetails() {

	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var departmentId = document.getElementById("selectDepartment").value;
	var employeeId = document.getElementById("selectEmployeeId").value;
	var shiftId = document.getElementById("shiftId").value;

	if (startDate == "" || endDate == "") {
		return;
	} else {

		if (departmentId == "" && employeeId == "" && shiftId == "") {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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

		} else if (employeeId == "" && shiftId == "") {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate,
					"departmentId" : departmentId
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
					$("#dataTable tbody").empty();

					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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

		} else if (departmentId == "" && employeeId == "") {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate,
					"shiftId" : shiftId
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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

		} else if (employeeId == "") {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate,
					"departmentId" : departmentId,
					"shiftId" : shiftId
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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

		} else if (shiftId == "") {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate,
					"departmentId" : departmentId,
					"employeeId" : employeeId
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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

		} else {

			$.ajax({
				type : 'GET',
				url : "loadShiftDetails",
				data : {
					"startDate" : startDate,
					"endDate" : endDate,
					"departmentId" : departmentId,
					"employeeId" : employeeId,
					"shiftId" : shiftId
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td>" + data[i].date + "</td><td>"
								+ data[i].weekday + "</td><td>"
								+ data[i].day_type + "</td><td>"
								+ data[i].shift + "</td><td>"
								+ data[i].department + "</td><td>"
								+ data[i].employee_name + "</td><td>"
								+ data[i].start_time + "</td><td>"
								+ data[i].end_time + "</td><td>"
								+ data[i].on_time + "</td><td>"
								+ data[i].off_time + "</td></tr>";

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
}