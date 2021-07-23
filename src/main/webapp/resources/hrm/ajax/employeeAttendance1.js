function loadAttendanceRecords() {

	var date = document.getElementById("date").value;
	var shiftId = document.getElementById("shiftId").value;
	var departmentId = document.getElementById("selectDepartment").value;
	var employeeId = document.getElementById("selectEmployeeId").value;

	if (date == "") {
		return;
	} else {

		if (departmentId == "" && employeeId == "" && shiftId == "") {

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date
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
								var result = ' <tr>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].attendanceId
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].date
										+ '</td>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].shiftId
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].shift
										+ '</td>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].departmentId
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].department
										+ '</td>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].employeeId
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].employee
										+ '</td>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].startTime
										+ '</td>'
										+ '<td style="display:none; height:4rem">'
										+ data[i].endTime
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].onTime
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].offTime
										+ '</td>'
										+ '<td style="height:4rem">'
										+ data[i].approvalStatus
										+ '</td>'
										+ '<td style="height:4rem">'
										+ '<a href="#" onclick="updateEmployeeAttendance(data[i].attendanceId, data[i].employee, data[i].departmentId, data[i].department, data[i].shiftId, data[i].shift, new Date(data[i].date), data[i].startTime, data[i].endTime)"><i class="far fa-edit"></i></a></td>'
										+ '</tr>';

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

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date,
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
								var result = " <tr>"
										+ "<td style='display:none'>"
										+ data[i].attendanceId
										+ "</td>"
										+ "<td>"
										+ data[i].date
										+ "</td>"
										+ "<td style='display:none'>"
										+ data[i].shiftId
										+ "</td>"
										+ "<td>"
										+ data[i].shift
										+ "</td>"
										+ "<td style='display:none'>"
										+ data[i].departmentId
										+ "</td>"
										+ "<td>"
										+ data[i].department
										+ "</td>"
										+ "<td style='display:none'>"
										+ data[i].employeeId
										+ "</td>"
										+ "<td>"
										+ data[i].employee
										+ "</td>"
										+ "<td style='display:none'>"
										+ data[i].startTime
										+ "</td>"
										+ "<td style='display:none'>"
										+ data[i].endTime
										+ "</td>"
										+ "<td>"
										+ data[i].onTime
										+ "</td>"
										+ "<td>"
										+ data[i].offTime
										+ "</td>"
										+ "<td>"
										+ data[i].approvalStatus
										+ "</td>"
										+ "<td>"
										+ "<button onClick='updateEmployeeAttendance("
										+ data[i].attendanceId
										+ ", "
										+ data[i].employee
										+ ", "
										+ data[i].departmentId
										+ ", "
										+ data[i].department
										+ ", "
										+ data[i].shiftId
										+ ", "
										+ data[i].shift
										+ ", new Date("
										+ data[i].date
										+ "), "
										+ data[i].startTime
										+ ", "
										+ data[i].endTime
										+ " )'><i class='far fa-edit'></i></button></td>"
										+ "</tr>";

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

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date,
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
								var result = ' <tr>'
										+ '<td style="display:none">'
										+ data[i].attendanceId
										+ '</td>'
										+ '<td>'
										+ data[i].date
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].shiftId
										+ '</td>'
										+ '<td>'
										+ data[i].shift
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].departmentId
										+ '</td>'
										+ '<td>'
										+ data[i].department
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].employeeId
										+ '</td>'
										+ '<td>'
										+ data[i].employee
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].startTime
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].endTime
										+ '</td>'
										+ '<td>'
										+ data[i].onTime
										+ '</td>'
										+ '<td>'
										+ data[i].offTime
										+ '</td>'
										+ '<td>'
										+ data[i].approvalStatus
										+ '</td>'
										+ '<td>'
										+ '<a href="#" onclick="updateEmployeeAttendance('
										+ data[i].attendanceId
										+ ', '
										+ data[i].employee
										+ ', '
										+ data[i].departmentId
										+ ', '
										+ data[i].department
										+ ', '
										+ data[i].shiftId
										+ ', '
										+ data[i].shift
										+ ', new Date('
										+ data[i].date
										+ '), '
										+ data[i].startTime
										+ ', '
										+ data[i].endTime
										+ ' )"><i class="far fa-edit"></i></a></td>'
										+ '</tr>';

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

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date,
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
								var result = ' <tr>'
										+ '<td style="display:none">'
										+ data[i].attendanceId
										+ '</td>'
										+ '<td>'
										+ data[i].date
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].shiftId
										+ '</td>'
										+ '<td>'
										+ data[i].shift
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].departmentId
										+ '</td>'
										+ '<td>'
										+ data[i].department
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].employeeId
										+ '</td>'
										+ '<td>'
										+ data[i].employee
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].startTime
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].endTime
										+ '</td>'
										+ '<td>'
										+ data[i].onTime
										+ '</td>'
										+ '<td>'
										+ data[i].offTime
										+ '</td>'
										+ '<td>'
										+ data[i].approvalStatus
										+ '</td>'
										+ '<td>'
										+ '<a href="#" onclick="updateEmployeeAttendance('
										+ data[i].attendanceId
										+ ', '
										+ data[i].employee
										+ ', '
										+ data[i].departmentId
										+ ', '
										+ data[i].department
										+ ', '
										+ data[i].shiftId
										+ ', '
										+ data[i].shift
										+ ', new Date('
										+ data[i].date
										+ '), '
										+ data[i].startTime
										+ ', '
										+ data[i].endTime
										+ ' )"><i class="far fa-edit"></i></a></td>'
										+ '</tr>';

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

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date,
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
								var result = ' <tr>'
										+ '<td style="display:none">'
										+ data[i].attendanceId
										+ '</td>'
										+ '<td>'
										+ data[i].date
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].shiftId
										+ '</td>'
										+ '<td>'
										+ data[i].shift
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].departmentId
										+ '</td>'
										+ '<td>'
										+ data[i].department
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].employeeId
										+ '</td>'
										+ '<td>'
										+ data[i].employee
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].startTime
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].endTime
										+ '</td>'
										+ '<td>'
										+ data[i].onTime
										+ '</td>'
										+ '<td>'
										+ data[i].offTime
										+ '</td>'
										+ '<td>'
										+ data[i].approvalStatus
										+ '</td>'
										+ '<td>'
										+ '<a href="#" onclick="updateEmployeeAttendance('
										+ data[i].attendanceId
										+ ', '
										+ data[i].employee
										+ ', '
										+ data[i].departmentId
										+ ', '
										+ data[i].department
										+ ', '
										+ data[i].shiftId
										+ ', '
										+ data[i].shift
										+ ', new Date('
										+ data[i].date
										+ '), '
										+ data[i].startTime
										+ ', '
										+ data[i].endTime
										+ ' )"><i class="far fa-edit"></i></a></td>'
										+ '</tr>';

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

			$
					.ajax({
						type : 'GET',
						url : "loadAttendanceRecords",
						data : {
							"date" : date,
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
								var result = ' <tr>'
										+ '<td style="display:none">'
										+ data[i].attendanceId
										+ '</td>'
										+ '<td>'
										+ data[i].date
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].shiftId
										+ '</td>'
										+ '<td>'
										+ data[i].shift
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].departmentId
										+ '</td>'
										+ '<td>'
										+ data[i].department
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].employeeId
										+ '</td>'
										+ '<td>'
										+ data[i].employee
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].startTime
										+ '</td>'
										+ '<td style="display:none">'
										+ data[i].endTime
										+ '</td>'
										+ '<td>'
										+ data[i].onTime
										+ '</td>'
										+ '<td>'
										+ data[i].offTime
										+ '</td>'
										+ '<td>'
										+ data[i].approvalStatus
										+ '</td>'
										+ '<td>'
										+ '<a href="#" onclick="updateEmployeeAttendance('
										+ data[i].attendanceId
										+ ', '
										+ data[i].employee
										+ ', '
										+ data[i].departmentId
										+ ', '
										+ data[i].department
										+ ', '
										+ data[i].shiftId
										+ ', '
										+ data[i].shift
										+ ', new Date('
										+ data[i].date
										+ '), '
										+ data[i].startTime
										+ ', '
										+ data[i].endTime
										+ ' )"><i class="far fa-edit"></i></a></td>'
										+ '</tr>';

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

function checkEmployeeShiftAllocation() {
	
	var employeeId = document.getElementById('selectEmployeeId').value;
	var date = document.getElementById('date').value;
	var shiftId = document.getElementById('shiftId').value;
	
	if(employeeId == "all" || employeeId == "" || shiftId == "" || date == "") {
		return;
	}
	else {
	 
		$
			.ajax({
				type : 'GET',
				url : "checkEmployeeShiftAllocation",
				data : {
					"date" : date,
					"employeeId" : employeeId,
					"shiftId" : shiftId
				},
				success : function(data) {
					console.log(data)
					if (data == true){
						return true;
					} else if (data == false){
						swal("This employee doesn't have a fixed shift or a shift allocated to selected date", "Continue if you want to add as an OT!", {
							icon : "warning",
							buttons : {
								confirm : {
									className : 'btn btn-warning'
								}
							},
						});
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
	
	function setOverTimeAndLateTime() {
		
		var oT = document.getElementById('onTime').value;
		var offT = document.getElementById('lateTime').value;
		var shiftId = document.getElementById('shiftId').value;
		
		if(shiftId == "" || onTime == "" || offTime == "") {
			var overTime = $("#overTime");
			var lateTime = $("#lateTime");
			overTime.empty();
			lateTime.empty();
			return;
		}
		else {
		 
			$
				.ajax({
					type : 'GET',
					url : "findShiftByIdAndCompany",
					data : {
						"shiftId" : shiftId
					},
					success : function(data) {
						var startTime = new Date("01/01/2007 " + data.startTime).getTime();
						var endTime = new Date("01/01/2007 " + data.endTime).getTime();
						var onTime = new Date("01/01/2007 " + oT).getTime();
						var offTime = new Date("01/01/2007 " + offT).getTime();
						var totalOverTime;
						var totalLateTime;
						if(onTime > startTime){
							totalLateTime = totalLateTime +  (onTime - startTime);
						} else if(onTime < startTime){
							totalOverTime = totalOverTime +  (startTime - onTime);
						}
						if(offTime > endTime){
							totalOverTime = totalOverTime +  (offTime - endTime);
						} else if(offTime < endTime){
							totalLateTime = totalLateTime +  (endTime - offTime);
						}
						
						console.log("Total Overtime : " + totalOverTime);
						console.log("Total Late Time : " + totalLateTime);
						
						var otH = new Date("01/01/2007 " + totalOverTime).getHours();
						var otM = new Date("01/01/2007 " + totalOverTime).getMinutes();
						
						var offtH = new Date("01/01/2007 " + totalLateTime).getHours();
						var offtM = new Date("01/01/2007 " + totalLateTime).getMinutes();
						
						var overTime = $("#overTime");
						var lateTime = $("#lateTime");
						overTime.empty();
						lateTime.empty();
					
						document.getElementById("overTime").value = otH + " hrs : " + otM + " minutes";
						document.getElementById("lateTime").value = offtH + " hrs : " + offtM + " minutes";
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
}