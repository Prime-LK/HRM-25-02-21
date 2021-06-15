function validateForm() {
	var description = document.getElementById('description').value;
	var startTime = document.getElementById('startTime').value;
	var endTime = document.getElementById('endTime').value;
	var continuing = document.getElementById('continuing');

	if (continuing.checked) {
		
		if(description == "") {
			swal("Description is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		}  else if(startTime == "") {
			swal("Start time is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		} else if(endTime == "") {
			swal("End time is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		}
		

		} else if (!(continuing.checked)) {
		
		if(description == "") {
			swal("Description is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		}  else if(startTime == "") {
			swal("Start time is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		} else if(endTime == "") {
			swal("End time is empty!", "", {
				icon : "warning",
				buttons : {
					confirm : {
						className : 'btn btn-danger'
					}
				},
			});
			return false;
		} else if(startTime > endTime){
			swal("Start time must be lesser than the end time in non continuing shifts!", "", {
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
}