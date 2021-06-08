function validateForm() {
	var employeeId = document.getElementById('selectEmployeeId').value;
	var startDate = document.getElementById('startDate').value;
	var endDate = document.getElementById('endDate').value;

	if (startDate == "") {
		swal("Start date is empty!", "", {
			icon : "warning",
			buttons : {
				confirm : {
					className : 'btn btn-danger'
				}
			},
		});
		return false;
	} else if (endDate == "") {
		swal("End date is empty!", "", {
			icon : "warning",
			buttons : {
				confirm : {
					className : 'btn btn-danger'
				}
			},
		});
		return false;
	} else if (employeeId == "All") {
		swal("Employee is empty!", "", {
			icon : "warning",
			buttons : {
				confirm : {
					className : 'btn btn-danger'
				}
			},
		});
		return false;
	}
}