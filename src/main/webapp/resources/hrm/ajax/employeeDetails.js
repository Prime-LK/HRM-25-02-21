function getDetails() {
	var x = document.getElementById("empID").value;	
	$.ajax({
		type : "GET",
		url : "getRelatedTableData",
		data : { "empID" : x },
		success : function(data) {
			if(x != null) {
				alert(x + " Employee already exists do you want to edit data check table !")
			}
				var result = "<tr>"
					    + "<td><a href=updateDetails?empID="
						+ data.detailsPK.empID.empID + "><img src='resources/img/edit.png' width='25px' height='25px'></a></td>"
						+ "<td>" + data.detailsPK.detailID + "</td>"
						+ "<td>" + data.detailsPK.empID.empID + "</td>"
						+ "<td>" + data.company.comID + "</td>"
						+ "<td>" + data.status + "</td>"
						+ "<td>" + data.category.category + "</td>"
					    + "<td>" + data.designation.designation + "</td>"
					    + "<td>" + data.empType.type + "</td>" 
					    + "<td>" + data.joinedDate + "</td>"
					    + "<td>" + data.basicSalary + "</td>" 
					    + "<td>" + data.department.department + "</td>"
					    + "<td>" + data.salaryGrade.grade + "</td>"
					    + "<td>" + data.location.location + "</td>"
					    + "<td>" + data.salaryRange.range + "</td>"
					    + "<td>" + data.jobProfile.profile + "</td>"
					    + "<td>" + data.reporting + "</td>"
					    + "<td>" + data.epfNo + "</td>"
					    + "<td>" + data.resignDate + "</td></tr>";
				$("#empDeTable tbody").append(result);
		},
		error : function(e) {
			alert("Employee ID Not Found " + x);
		}
	});
}

