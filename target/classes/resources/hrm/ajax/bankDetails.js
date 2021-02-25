function loadGrid() {
//	alert("Hello world");
	var x = document.getElementById("empID").value;
	$("#tblBankDetails tbody").empty();
	
	$.ajax({
		type: "GET",
		url: "loadEmps",
		data:{"empID" : x},
		success:function(data) {
			$("tblBankDetails tbody").empty();
			for (var i = 0; i < data.length; i++) {

				var result = "<tr><td>"
						+ data[i].bPK.acc+ " </td><td> "
						+ data[i].bankid+ " </td><td> " 
						+  data[i].branch+ " </td><td> " 
						+"<a href=updateBa?empID="
						+ data[i].bPK.emp.empID
						+"&acc="
						+data[i].bPK.acc+
						"><i class='fas fa-pen'></i></a>"
						
						+ "</td></tr>";
				$("#tblBankDetails tbody").append(result);
			}
		},error:function() {
			alert("No ID Found");
		}
	});
}