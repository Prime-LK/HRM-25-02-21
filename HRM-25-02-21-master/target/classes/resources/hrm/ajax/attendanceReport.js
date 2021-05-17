function loadAttendanceSheet() {

	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var selectEmployee = document.getElementById("selectEmployeeId").value;
	console.log(startDate);
	console.log(selectEmployee);
	console.log(endDate);
	if (startDate == "" || endDate == "" || selectEmployee == "") {
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "loadAttendanceSheet",
					data : {
						"startDate" : startDate,
						"endDate" : endDate,
						"employeeId" : selectEmployee
					},
					success : function(data) {
						$("#dataTable tbody").empty();
						for (var i = 0; i < data.length; i++) {
							var result = "<tr><td>" 
								  		 + data[i].date + "</td><td>" 
								  		 + data[i].weekday + "</td><td>"
								  		 + data[i].day_type + "</td><td>"
								  		 + data[i].on_time + "</td><td>"
								  		 + data[i].off_time + "</td><td>"
								  		 + data[i].worked_time + "</td><td>"
								  		 + data[i].over_time + "</td><td>"
								  		 + data[i].short_time + "</td><td>" 
								  		 + data[i].attendance_description + 
								  		 "</td></tr>";
						
							$("#dataTable tbody").append(result);
							
						}
					},
					error : function() {
						alert("No attendance detais for this employee on the selected month!");
					}

				});
	}
}