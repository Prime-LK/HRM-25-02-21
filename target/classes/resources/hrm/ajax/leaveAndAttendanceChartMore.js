function getTableData() {
	var y = document.getElementById("depID").value;
	$.ajax({
		type: "GET",
		url: "getChartMoreData",
		data: {"depID": y},
		success:function(data) {
			$("#tableDetailsData tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" 
							 + "<td>" + data[i][0] + "</td>" 
							 + "<td>" + data[i][1] + " " + data[i][2] + "</td>" 
							 + "<td>" + data[i][3] + "</td>"
							 + "<td>" + data[i][4] + "</td>"
							 + "</tr>";
				$("#tableDetailsData tbody").append(result);
			}
		},
		error:function(e) {
			alert("Details Not Found");
		}
	});
}