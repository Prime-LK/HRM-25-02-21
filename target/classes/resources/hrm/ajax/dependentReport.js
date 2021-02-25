function loadRelatedDep() {
	var x = document.getElementById("depID").value;
//	console.log(x);
	$.ajax({
		type: "GET",
		url: "loadData",
		data: {"depID": x},
		success:function(data) {
			$("#tableEmpDep tbody").empty();
//			alert(data);
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" 
					  		 + data[i].detailsPK.empID.empID + "</td><td>" 
					  		 + data[i].detailsPK.empID.name + " " + data[i].detailsPK.empID.lastname + "</td></tr>";
				$("#tableEmpDep tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}