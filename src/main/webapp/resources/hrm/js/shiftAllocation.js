function validateForm() {  
	
	let sDate = document.getElementById('startDate').value;
	let eDate = document.getElementById('endDate').value;
	let sId = document.getElementById('shiftId').value;
	let dId = document.getElementById('selectDepartment').value;
	let eId = document.getElementById('selectEmployeeId').value;

	let sd = new Date(sDate);
	let ed = new Date(eDate);

	if (document.getElementById('allEmployees').checked) {

		console.log("Checked");

		if(sDate == ""){
			alert("Please set a start date!")
			document.getElementById('startDate').required = true;
			return false;
		} else if (eDate == ""){
			alert("Please set an end date!")
			document.getElementById('endDate').required = true;
			return false;
		} else if (sId == ""){
			alert("Please select a shift!")
			document.getElementById('shiftId').required = true;
			return false;
		} else if (dId == "") {
			alert("Please select a department!")
			document.getElementById('selectDepartment').required = true;
			return false;
		} else if (sd > ed) {
			alert("Invalid Date Range! Start Date must be lesser or equal to the End Date.");
			return false;
		} else {
			return true;
		}
	} else {

		console.log("Not Checked");

		if(sDate == ""){
			alert("Please set a start date!")
			document.getElementById('startDate').required = true;
			return false;
		} else if (eDate == ""){
			alert("Please set an end date!")
			document.getElementById('endDate').required = true;
			return false;
		} else if (sId == ""){
			alert("Please select a shift!")
			document.getElementById('shiftId').required = true;
			return false;
		} else if (dId == "") {
			alert("Please select a department!")
			document.getElementById('selectDepartment').required = true;
			return false;
		} else if (eId == "") {
			alert("Please select an employee!")
			document.getElementById('selectEmployeeId').required = true;
			return false;
		} else if (sd > ed) {
			alert("Invalid Date Range! Start Date must be lesser or equal to the End Date.");
			return false;
		} else {
			return true;
		}
	}
}
