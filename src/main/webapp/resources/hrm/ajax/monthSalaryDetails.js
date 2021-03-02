//get header with related fields
function getDefaultHeader() {
	$.ajax({	
				type : "GET",
				url : "loadRelatedHeader",
				success : function(data) {
					if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'Yes') {

						window.location.href = "getEmpMonthSalaryDetailsPage01";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'Yes') {
						
							window.location.href = "getEmpMonthSalaryDetailsPage03";
						    

					} else if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'No') {

						window.location.href = "getEmpMonthSalaryDetailsPage02";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'No') {
						
							window.location.href = "getEmpMonthSalaryDetailsPage04";

					}
				},
				error : function(e) {
					alert("Error found when data comming to page");
				}
			});
}