function validateForm() {

	let sDate = document.getElementById('startDate').value;
	let eDate = document.getElementById('endDate').value;

	let sd = new Date(sDate);
	let ed = new Date(eDate);

	if (sd > ed) {
		swal(
				"Invalid Date Range! Start Date must be lesser or equal to the End Date!",
				"", {
					icon : "warning",
					buttons : {
						confirm : {
							className : 'btn btn-warning'
						}
					},
				});
		return false;
	} else {
		return true;
	}
}

/*function validateForm() {

	let sDate = document.getElementById('startDate').value;
	let eDate = document.getElementById('endDate').value;
	let sId = document.getElementById('shiftId').value;
	let dId = document.getElementById('selectDepartment').value;
	let eId = document.getElementById('selectEmployeeId').value;

	let sd = new Date(sDate);
	let ed = new Date(eDate);

	if (document.getElementById('allEmployees').checked) {

		if (sDate == "") {
			swal("Please select a start date!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('startDate').required = true;
			return false;
		} else if (eDate == "") {
			swal("Please select an end date!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('endDate').required = true;
			return false;
		} else if (sId == "") {
			swal("Please select a shift!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('shiftId').required = true;
			return false;
		} else if (dId == "") {
			swal("Please select a department!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('selectDepartment').required = true;
			return false;
		} else if (sd > ed) {
			swal(
					"Invalid Date Range! Start Date must be lesser or equal to the End Date!",
					"", {
						icon : "warning",
						buttons : {
							confirm : {
								className : 'btn btn-danger'
							}
						},
					});
			return false;
		} else {
			return true;
		}
	} else {

		if (sDate == "") {
			swal("Please select a start date!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('startDate').required = true;
			return false;
		} else if (eDate == "") {
			swal("Please select an end date!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('endDate').required = true;
			return false;
		} else if (sId == "") {
			swal("Please select a shift!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('shiftId').required = true;
			return false;
		} else if (dId == "") {
			swal("Please select a department!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('selectDepartment').required = true;
			return false;
		} else if (eId == "") {
			swal("Please select an employee!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			document.getElementById('selectEmployeeId').required = true;
			return false;
		} else if (sd > ed) {
			swal(
					"Invalid Date Range! Start Date must be lesser or equal to the End Date!",
					"", {
						icon : "warning",
						buttons : {
							confirm : {
								className : 'btn btn-danger'
							}
						},
					});
			return false;
		} else {
			return true;
		}
	}
}
*/