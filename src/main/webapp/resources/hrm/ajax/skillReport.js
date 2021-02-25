function getSkEmps() {
	// alert("Hello world");
	var str = document.getElementById("rsid").value;
	if (str == "") {
			$("#talbeSkiillRe tbody").empty();
		return;
	} else {
		$.ajax({
					type : 'GET',
					url : "loadDropdown",
					data : {
						"sid" : str
					},
					success : function(data) {
//						alert(data.quaPK.empID.empID);
						$("#talbeSkiillRe tbody").empty();
						for (var i = 0; i < data.length; i++) {

							var result = "<tr><td>"
									+ data[i].skillPK.empID.empID+ " </td><td> "
									+ data[i].skillPK.empID.name+ " </td></tr>";
							$("#talbeSkiillRe tbody").append(result);
						}
					},error:function() {
						alert("Details Not Found");
					}
				});
	}
}/**
 * 
 */