/*function myFunction() {

	var month = new Array();
	month[0] = "January";
	month[1] = "February";
	month[2] = "March";
	month[3] = "April";
	month[4] = "May";
	month[5] = "June";
	month[6] = "July";
	month[7] = "August";
	month[8] = "September";
	month[9] = "October";
	month[10] = "November";
	month[11] = "December";

	var input = document.getElementById('date').value;
	var d = new Date(input);

	if (!!d.valueOf()) { // Valid date
		year = d.getFullYear();
		month = month[d.getMonth()];
		day = d.getDate();
	} else {  Invalid date 
	} 

	document.getElementById("yy").value = year;
	document.getElementById("mm").value = month;
	document.getElementById("dd").value = day;

}
 */

function load() {

	var s = document.getElementById("pickYear").value;
	var start = new Date(s);
	var e = document.getElementById("endYear").value;
	var end = new Date(e);

	while (start <= end) {
		start.setDate(start.getDate() + 1);

		function convert(str) {
			var date = new Date(start), mnth = ("0" + (date.getMonth() + 1))
					.slice(-2), day = ("0" + date.getDate()).slice(-2);
			return [ date.getFullYear(), mnth, day ].join("-");
		}
			
		console.log(convert("Thu Jun 09 2011 00:00:00 GMT+0530 (India Standard Time)"))
		
		document.getElementById("year").value = convert("Thu Jun 09 2011 00:00:00 GMT+0530 (India Standard Time)")

	/*	jQuery(document).ready(function($) {

			$("#btndate").click(function(load) {

				var data = {}
				data["Date"] = $("#date").val();

				$.ajax({
					type : "POST",
					url : "/postcalendar",
					data : date,
					success : function(data) {

					},
					error : function(e) {
						$("#btndate").prop("disabled", false);
						//...
					}
				});

			});

		});*/

		
		}

	}


