function loadEmployeesByDepartment() {

	var selectDepartment = document.getElementById("selectDepartment").value;
	if (selectDepartment == "") {
		var selectEmployee = $("#selectEmployeeId"), option = "";
		selectEmployee.empty();
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "loadEmployeesByDepartment",
					data : {
						"depID" : selectDepartment
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
						var selectEmployee = $("#selectEmployeeId"), option = "";
						selectEmployee.empty();
						selected_option = "<option value='' selected>SELECT</option>";
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

function loadEmployeeShiftDetails() {

	var selectDate = window.document.getElementById("date");
	var date = selectDate.value;
	var mydate = window.document.getElementById("date");
	var olddate = mydate.value;
	var selectEmployee = document.getElementById("selectEmployeeId").value;
	var selectShift = document.getElementById("shiftId").value;
	if (olddate == "" || selectShift == "" || selectEmployee == "") {
		var startTime = $("#startTime");
		var endTime = $("#endTime");
		startTime.empty();
		endTime.empty();
		return;
	} else {

		$.ajax({
			type : 'GET',
			url : "loadEmployeeShiftDetails",
			data : {
				"date" : olddate,
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
				var startTime = $("#startTime");
				var endTime = $("#endTime");
				startTime.empty();
				endTime.empty();
				document.getElementById("startTime").value = data.startTime;
				document.getElementById("endTime").value = data.endTime;
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

function updateEmployeeAttendance(id, name, depId, department, sftId, sftName,
		dte, sT, eT) {
	$.ajax({
		type : 'GET',
		url : "updateEmployeeAttendance",
		data : {
			"attendanceId" : id
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

			var d = formatDate(dte);
			var d2 = formatDate2(data.date);

			var aId = $("#attendanceId");
			var dId = $("#selectDepartment");
			var date = $("#date");
			var on = $("#onTime");
			var off = $("#offTime");
			aId.empty();
			date.empty();
			on.empty();
			off.empty();

			var dId = $("#selectDepartment"), option = "";
			dId.empty();
			option = option + "<option value='" + depId + "' selected> "
					+ department + " </option>";
			dId.append(option);

			var sId = $("#shiftId"), option = "";
			sId.empty();
			option = option + "<option value='" + sftId + "' selected> "
					+ sftName + " </option>";
			sId.append(option);

			document.getElementById("attendanceId").value = data.attendanceId;
			document.getElementById("date").value = d2;
			document.getElementById("startTime").value = sT;
			document.getElementById("endTime").value = eT;
			document.getElementById("onTime").value = data.onTime;
			document.getElementById("offTime").value = data.offTime;

			var eId = $("#selectEmployeeId"), option = "";
			eId.empty();
			option = option + "<option value='" + data.employeeId
					+ "' selected> " + name + " </option>";
			eId.append(option);
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

function formatDate(date) {
	var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
			+ d.getDate(), year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ year, day, month ].join('-');
}

function formatDate2(date) {
	var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
			+ d.getDate(), year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ year, month, day ].join('-');
}
