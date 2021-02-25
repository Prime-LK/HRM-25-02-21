function loadRelatedempdetails() {
	var x = document.getElementById("depID").value;
	
//	console.log(x);
	$.ajax({
		type: "GET",
		url: "getemptable",
		data: {"depID": x},
		success:function(data) {
			$("#tableemp tbody").empty();
	
			for (var i = 0; i < data.length; i++) {
				

				var result = "<tr><td>" +  data[i].detailsPK.empID.empID  + "</td><td>" 
  		 				+ data[i].detailsPK.empID.name + " " + data[i].detailsPK.empID.lastname + "</td><td>" 
  		 				+ "<a href= 'submitEmpReport?empID=" + data[i].detailsPK.empID.empID + "' class='d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm'> Generate Report</a>" 
  		 				+ "</td></tr>";
				$("#tableemp tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}