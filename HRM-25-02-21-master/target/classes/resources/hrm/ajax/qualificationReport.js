function getEmps() {
	// alert("Hello world");
	var str = document.getElementById("quaId").value;
// alert("Hello");
	if (str == "") {
			$("#talbeEmpQm tbody").empty();
			alert("Hello");
		return;
	} else {
		$.ajax({
					type : 'GET',
					url : "loadQuaEmp",
					data : {
						"qid" : str
					},
					success : function(data) {

						$("#talbeEmpQm tbody").empty();
						for (var i = 0; i < data.length; i++) {

							var result = "<tr><td>"
									+ data[i].quaPK.empID.empID+ " </td><td> "
									+ data[i].quaPK.empID.name+ " </td><td> "
									+ data[i].desc+ "</td></tr>"
							$("#talbeEmpQm tbody").append(result);
						}
					},error:function() {
						alert("Qualification Not Found");
					}
				});
	}
}