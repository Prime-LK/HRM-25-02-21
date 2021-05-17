function loadGrid() {
//	alert("Hello world");
	var x = document.getElementById("empID").value;
	$("#tableWorkExp tbody").empty();
	
	$.ajax({
		type: "GET",
		url: "getEmployee",
		data:{"empID" : x},
		success:function(data) {
			$("#tableWorkExp tbody").empty();
			for (var i = 0; i < data.length; i++) {

				/*var result = "<tr><td> " 
						+"<a href=updateWexp?empID="
						+ data[i].employeeWorkExperiencePK.empID.empID
						+"&expID="
						+data[i].employeeWorkExperiencePK.expId
						+"> <i class='far fa-edit'></i></a>"
						+ "</td><td id='company'>"
						+ data[i].companyName + " <br><div id='designation'> " 
						+  data[i].designation + " </div></td><td  id='joinDate'>" 
						+  data[i].joinDate + "<br><div id='regignDate'> " 
						+  data[i].resignDate + " </div></td></tr>";
				$("#tableWorkExp tbody").append(result);*/
				
				var result = "<tr>" +
						"<td>" + data[i].companyName + "</td>" +
						"<td>" + data[i].designation + "</td>" +
						"<td>" + data[i].joinDate + "</td>" +
						"<td>" + data[i].resignDate + "</td>" +
						"<td> " 
						+"<a href=updateWexp?empID="
						+ data[i].employeeWorkExperiencePK.empID.empID
						+"&expID="
						+data[i].employeeWorkExperiencePK.expId
						+"> <i class='far fa-edit'></i></a>"
						+ "</td></tr>";
				$("#tableWorkExp tbody").append(result);
			}
		},error:function() {
			alert("No ID Found");
		}
	});
}

