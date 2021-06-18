function validateForm() {
	
	var employeeId = document.getElementById('selectEmployeeId').value;
	var date = document.getElementById('date').value;
	var shiftId = document.getElementById('shiftId').value;
	var onTime = document.getElementById('onTime').value;
	var offTime = document.getElementById('offTime').value;

	if(employeeId == "" || employeeId == "All") {
		swal("Please select an employee!", "", {
			icon : "warning",
			buttons : {
				confirm : {
					className : 'btn btn-danger'
				}
			},
		});
		return false;
	} else if(date == "") {
			swal("Please select a date!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		}  else if(shiftId == "") {
			swal("Please select a shift!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		} else if(onTime == "") {
			swal("Please select a on time!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		} else if(offTime == ""){
			swal("Please select a off time!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		}
		else {
			return true;
		}
}