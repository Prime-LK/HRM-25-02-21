//get header with related fields
function getDefaultHeader() {
	$.ajax({	
				type : "GET",
				url : "loadRelatedHeader2",
				success : function(data) {
					console.log('data');
					if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'Yes') {

						window.location.href = "salaryMonthEndFor01";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'Yes') {
						
							window.location.href = "salaryMonthEndFor03";
						    

					} else if (data.multipayperiod == 'Yes'
							&& data.multipaycode == 'No') {

						window.location.href = "salaryMonthEndFor02";

					} else if (data.multipayperiod == 'No'
							&& data.multipaycode == 'No') {
						
							window.location.href = "salaryMonthEndFor04";

					}
				},
				error : function(e) {
					alert("Error found when data comming to page");
				}
			});
}
