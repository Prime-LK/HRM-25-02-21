//get header with related fields
function getDefaultHeader() {
	$.ajax({	
				type : "GET",
				url : "loadRelatedHeader2",
				success : function(data) {
					if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'Yes') {

						window.location.href = "getEmpMonthSalaryDetailsPage01";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'Yes') {
						
							window.location.href = "getPage3";
						    

					} else if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'No') {

						window.location.href = "getPage2";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'No') {
						
							window.location.href = "getPage4";

					}
				},
				error : function(e) {
					alert("Error found when data comming to page");
				}
			});
}
