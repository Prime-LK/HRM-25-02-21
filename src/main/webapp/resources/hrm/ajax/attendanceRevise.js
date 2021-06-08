function loadEmployeeAttendanceDetails() {

	var selectDate = window.document.getElementById("date");
	var date = selectDate.value;
	var selectEmployee = document.getElementById("selectEmployeeId").value;
	var selectShift = document.getElementById("shiftId").value;
	if (date == "" || selectShift == "" || selectEmployee == "") {
		var onTime = $("#onTime");
		var offTime = $("#offTime");
		onTime.empty();
		offTime.empty();
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "loadEmployeeAttendanceDetails",
					data : {
						"date" : date,
						"shiftId" : selectShift,
						"employeeId" : selectEmployee
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
						var attendanceId = $("#attendanceId");
						var onTime = $("#onTime");
						var offTime = $("#offTime");
						var blank = "No Record";
						attendanceId.empty();
						onTime.empty();
						offTime.empty();
						if (data.onTime == undefined
								&& data.offTime == undefined) {
							swal("No data is available for the selected parameters!", "", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
							document.getElementById("onTime").value = blank;
							document.getElementById("offTime").value = blank;
						} else if (data.onTime == undefined) {
							document.getElementById("onTime").value = blank;
							document.getElementById("offTime").value = data.offTime;
						} else if (data.offTime == undefined) {
							document.getElementById("onTime").value = data.onTime;
							document.getElementById("offTime").value = blank;
						}
						document.getElementById("attendanceId").value = data.attendanceId;
						document.getElementById("onTime").value = data.onTime;
						document.getElementById("offTime").value = data.offTime;
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