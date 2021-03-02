function loadGrid() {
//	alert("Hello world");
	var x = document.getElementById("empID").value;
	$("#tableDType tbody").empty();
	
	$.ajax({
		type: "GET",
		url: "findEmps",
		data:{"empID" : x},
		success:function(data) {
			$("tableDType tbody").empty();
			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td> " 
//						+"<a href=updateEDep?empID="
//						+ data[i].dependentPK.empID.empID
//						+"&dTypeID="
//						+data[i].dependentPK.dTypeID.dTypeID+
//						"><img src='resources/img/edit.png' width='25px' height='25px'></a>"
//						+ "</td><td><div id='depType'>"
//						+ data[i].dependentPK.dTypeID.dType+ " </div></td><td><div id='name'> "
//						+ data[i].name+ "<br><div id='dob'> " 
//						+  data[i].dob+ " </div><div id='contact'>" 
//						+  data[i].conNo+ " </div></div></td></tr>";
//				$("#tableDType tbody").append(result);			
				var result = "<tr><td>"
					+ data[i].dependentPK.dTypeID.dType
					+ "</td>"
					+ "<td>"
					+ data[i].name
					+ "</td>"
					+ "<td>"
					+ data[i].dob
					+ "</td>"
					+ "<td>"
					+ data[i].conNo
					+ "</td>"
					+ "<td><a href=updateEDep?empID="
					+ data[i].dependentPK.empID.empID
					+"&dTypeID="
					+data[i].dependentPK.dTypeID.dTypeID+
					"> <i class='far fa-edit'></i></a></td></tr>";
				$("#tableDType tbody").append(result);
			}
		},error:function() {
			alert("No ID Found");
		}
	});
}