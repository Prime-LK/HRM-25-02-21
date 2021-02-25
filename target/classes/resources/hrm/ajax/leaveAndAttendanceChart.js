function loadChart() {
	$
			.ajax({
				type : 'GET',
				url : "getDepNameToChart",
				success : function(data) {

					// $("#deps").empty();
					for (var i = 0; i < data.length; i++) {

						var result = "<input type='text' class='form-control' id='dep' name='dep' value='"
								+ data[i][0] + "' readOnly/>";
						$("#deps").append(result);
					}
				},
				error : function() {
					alert("Chart Data Not Found");
				}

			});

	$.ajax({
				type : 'GET',
				url : "getChartDateRelatedDep",
				success : function(data) {
					$("#chartTbl tbody").empty();
					for (var i = 0; i < data.length; i++) {
						
						var result01 = "<label id='lbl01'>"+ data[i][1] +"</label><span id='spn01'>( "+ data[i][2] +" Emps )</span>";
						$("#colName").append(result01);
						
						var result02 = "<div class='col-5 ml-4'><progress id='progress01' value='"+data[i][2]+"'" +
								" max='50'>"+data[i][2]+"</progress></div>";
					$("#detailsDiv").append(result02);
					}
				},
				error : function() {
					alert("Presented Employees Not Found");
				}
			});

}
