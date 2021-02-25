function loadAttendanceRecords() {

	var date = document.getElementById("date").value;
	var shiftId = document.getElementById("shiftId").value;
	var departmentId = document.getElementById("selectDepartment").value;
	var employeeId = document.getElementById("selectEmployeeId").value;
	
	if (date == "") {
		return;
	} else {
		
		if (departmentId == "" && employeeId == "" && shiftId == "") {
			
			console.log(date);
			
			$
			.ajax({
				type : 'GET',
				url : "loadAttendanceRecords",
				data : {
					"date" : date
				},
				success : function(data) {
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						 var result = ' <tr>' +
						 	'<td style="display:none; height:4rem">' + data[i].attendanceId + '</td>' +
					        '<td style="height:4rem">' + data[i].date + '</td>' +
					        '<td style="display:none; height:4rem">' + data[i].shiftId + '</td>' +
					        '<td style="height:4rem">' + data[i].shift + '</td>' +
					        '<td style="display:none; height:4rem">' + data[i].departmentId + '</td>' +
					        '<td style="height:4rem">' + data[i].department + '</td>' +
					        '<td style="display:none; height:4rem">' + data[i].employeeId + '</td>' +
					        '<td style="height:4rem">' + data[i].employee + '</td>' +
					        '<td style="display:none; height:4rem">' + data[i].startTime + '</td>' +
					        '<td style="display:none; height:4rem">' + data[i].endTime + '</td>' +
					        '<td style="height:4rem">' + data[i].onTime + '</td>' +
					        '<td style="height:4rem">' + data[i].offTime + '</td>' +
					        '<td style="height:4rem">' + data[i].approvalStatus + '</td>' +
							'<td style="height:4rem">' + '<a href="#" onclick="updateEmployeeAttendance(data[i].attendanceId, data[i].employee, data[i].departmentId, data[i].department, data[i].shiftId, data[i].shift, new Date(data[i].date), data[i].startTime, data[i].endTime)"><i class="far fa-edit"></i></a></td>' +
							'</tr>';
					
						$("#dataTable tbody").append(result);
					
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		} else if (employeeId == "" && shiftId == "") {
			
			console.log(date);
			console.log(departmentId);
			
			$
			.ajax({
				type : 'GET',
				url : "loadAttendanceRecords",
				data : {
					"date" : date,
					"departmentId" : departmentId
				},
				success : function(data) {
					$("#dataTable tbody").empty();

					for (var i = 0; i < data.length; i++) {
						var result = ' <tr>' +
						'<td style="display:none">' + data[i].attendanceId + '</td>' +
				        '<td>' + data[i].date + '</td>' +
				        '<td style="display:none">' + data[i].shiftId + '</td>' +
				        '<td>' + data[i].shift + '</td>' +
				        '<td style="display:none">' + data[i].departmentId + '</td>' +
				        '<td>' + data[i].department + '</td>' +
				        '<td style="display:none">' + data[i].employeeId + '</td>' +
				        '<td>' + data[i].employee + '</td>' +
				        '<td style="display:none">' + data[i].startTime + '</td>' +
				        '<td style="display:none">' + data[i].endTime + '</td>' +
				        '<td>' + data[i].onTime + '</td>' +
				        '<td>' + data[i].offTime + '</td>' +
				        '<td>' + data[i].approvalStatus + '</td>' +
				        '<td>' + '<a href="#" onclick="updateEmployeeAttendance(' + data[i].attendanceId + ', ' + data[i].employee + ', ' + data[i].departmentId + ', ' + data[i].department + ', ' + data[i].shiftId + ', ' + data[i].shift + ', new Date(' + data[i].date + '), ' + data[i].startTime +', ' + data[i].endTime + ' )"><i class="far fa-edit"></i></a></td>' +
				      '</tr>';
					
						$("#dataTable tbody").append(result);
						
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		} else if (departmentId == "" && employeeId == "") {
			
			console.log(date);
			console.log(shiftId);
			
			$
			.ajax({
				type : 'GET',
				url : "loadAttendanceRecords",
				data : {
					"date" : date,
					"shiftId" : shiftId
				},
				success : function(data) {
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = ' <tr>' +
						'<td style="display:none">' + data[i].attendanceId + '</td>' +
				        '<td>' + data[i].date + '</td>' +
				        '<td style="display:none">' + data[i].shiftId + '</td>' +
				        '<td>' + data[i].shift + '</td>' +
				        '<td style="display:none">' + data[i].departmentId + '</td>' +
				        '<td>' + data[i].department + '</td>' +
				        '<td style="display:none">' + data[i].employeeId + '</td>' +
				        '<td>' + data[i].employee + '</td>' +
				        '<td style="display:none">' + data[i].startTime + '</td>' +
				        '<td style="display:none">' + data[i].endTime + '</td>' +
				        '<td>' + data[i].onTime + '</td>' +
				        '<td>' + data[i].offTime + '</td>' +
				        '<td>' + data[i].approvalStatus + '</td>' +
				        '<td>' + '<a href="#" onclick="updateEmployeeAttendance(' + data[i].attendanceId + ', ' + data[i].employee + ', ' + data[i].departmentId + ', ' + data[i].department + ', ' + data[i].shiftId + ', ' + data[i].shift + ', new Date(' + data[i].date + '), ' + data[i].startTime +', ' + data[i].endTime + ' )"><i class="far fa-edit"></i></a></td>' +
				      '</tr>';
					
						$("#dataTable tbody").append(result);
						
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		} else if (employeeId == "") {
			
			console.log(date);
			console.log(departmentId);
			console.log(shiftId);
			
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = ' <tr>' +
						'<td style="display:none">' + data[i].attendanceId + '</td>' +
				        '<td>' + data[i].date + '</td>' +
				        '<td style="display:none">' + data[i].shiftId + '</td>' +
				        '<td>' + data[i].shift + '</td>' +
				        '<td style="display:none">' + data[i].departmentId + '</td>' +
				        '<td>' + data[i].department + '</td>' +
				        '<td style="display:none">' + data[i].employeeId + '</td>' +
				        '<td>' + data[i].employee + '</td>' +
				        '<td style="display:none">' + data[i].startTime + '</td>' +
				        '<td style="display:none">' + data[i].endTime + '</td>' +
				        '<td>' + data[i].onTime + '</td>' +
				        '<td>' + data[i].offTime + '</td>' +
				        '<td>' + data[i].approvalStatus + '</td>' +
				        '<td>' + '<a href="#" onclick="updateEmployeeAttendance(' + data[i].attendanceId + ', ' + data[i].employee + ', ' + data[i].departmentId + ', ' + data[i].department + ', ' + data[i].shiftId + ', ' + data[i].shift + ', new Date(' + data[i].date + '), ' + data[i].startTime +', ' + data[i].endTime + ' )"><i class="far fa-edit"></i></a></td>' +
				      '</tr>';
					
						$("#dataTable tbody").append(result);
						
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		} else if (shiftId == "") {
			
			console.log(date);
			console.log(departmentId);
			console.log(employeeId);
			
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = ' <tr>' +
						'<td style="display:none">' + data[i].attendanceId + '</td>' +
				        '<td>' + data[i].date + '</td>' +
				        '<td style="display:none">' + data[i].shiftId + '</td>' +
				        '<td>' + data[i].shift + '</td>' +
				        '<td style="display:none">' + data[i].departmentId + '</td>' +
				        '<td>' + data[i].department + '</td>' +
				        '<td style="display:none">' + data[i].employeeId + '</td>' +
				        '<td>' + data[i].employee + '</td>' +
				        '<td style="display:none">' + data[i].startTime + '</td>' +
				        '<td style="display:none">' + data[i].endTime + '</td>' +
				        '<td>' + data[i].onTime + '</td>' +
				        '<td>' + data[i].offTime + '</td>' +
				        '<td>' + data[i].approvalStatus + '</td>' +
				        '<td>' + '<a href="#" onclick="updateEmployeeAttendance(' + data[i].attendanceId + ', ' + data[i].employee + ', ' + data[i].departmentId + ', ' + data[i].department + ', ' + data[i].shiftId + ', ' + data[i].shift + ', new Date(' + data[i].date + '), ' + data[i].startTime +', ' + data[i].endTime + ' )"><i class="far fa-edit"></i></a></td>' +
				      '</tr>';
					
						$("#dataTable tbody").append(result);
						
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		} else {
			
			console.log(date);
			console.log(departmentId);
			console.log(employeeId);
			console.log(shiftId);
			
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
					$("#dataTable tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = ' <tr>' +
				        '<td style="display:none">' + data[i].attendanceId + '</td>' +
				        '<td>' + data[i].date + '</td>' +
				        '<td style="display:none">' + data[i].shiftId + '</td>' +
				        '<td>' + data[i].shift + '</td>' +
				        '<td style="display:none">' + data[i].departmentId + '</td>' +
				        '<td>' + data[i].department + '</td>' +
				        '<td style="display:none">' + data[i].employeeId + '</td>' +
				        '<td>' + data[i].employee + '</td>' +
				        '<td style="display:none">' + data[i].startTime + '</td>' +
				        '<td style="display:none">' + data[i].endTime + '</td>' +
				        '<td>' + data[i].onTime + '</td>' +
				        '<td>' + data[i].offTime + '</td>' +
				        '<td>' + data[i].approvalStatus + '</td>' +
				        '<td>' + '<a href="#" onclick="updateEmployeeAttendance(' + data[i].attendanceId + ', ' + data[i].employee + ', ' + data[i].departmentId + ', ' + data[i].department + ', ' + data[i].shiftId + ', ' + data[i].shift + ', new Date(' + data[i].date + '), ' + data[i].startTime +', ' + data[i].endTime + ' )"><i class="far fa-edit"></i></a></td>' +
				      '</tr>';
					
						$("#dataTable tbody").append(result);
						
					}
				},
				error : function() {
					alert("No attendance detais for this employee on the selected date!");
				}

			});
			
		}
		
	}
}