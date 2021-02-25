function loadGrid() {
			var x = document.getElementById("empID").value;
			$("#tableEmpQua tbody").empty();
			$.ajax({
				type : 'GET',
				url : "getQDtails",
				data : {
					"empID" : x
				},
				success : function(data) {

					$("#tableEmpQua tbody").empty();
					for (var i = 0; i < data.length; i++) {

						var result = "<tr><td><a href=updateEq?empID="
								+ data[i].quaPK.empID.empID
								+"&qid="
								+data[i].quaPK.qMaster.qid
								+"><img src='resources/img/edit.png' width='25px' height='25px'></a></td>"
								+ "<td><div id='qua'>"
								+ data[i].quaPK.qMaster.qualification
								+ " </div></td><td id='detials'>" + data[i].desc + "<br>" 
								+ "<div id='award'>" + data[i].award + " </div></td>"
								+ "</tr>";
						$("#tableEmpQua tbody").append(result);
					}
				},
				error : function() {
// alert("EMPLOYEE ID NOT FOUND!!");
				}

			});

		}

function loadMemGrid() {
	var x = document.getElementById("empID").value;
	$("#tableEmpMem tbody").empty();

	$
			.ajax({
				type : 'GET',
				url : "getMDtails",
				data : {
					"empID" : x
				},
				success : function(data) {

					$("#tableEmpMem tbody").empty();
					for (var i = 0; i < data.length; i++) {

						var result = "<tr><td>"
								+ "<a href=updateMembership?eid="
								+ data[i].employeeMembershipPK.empID.empID
								+ "&memID="
								+ data[i].employeeMembershipPK.membershipInformation.memID
								+ "><img src='resources/img/edit.png' width='25px' height='25px'>" +
								"</a> </td><td><div id='mem'>"
								+ data[i].employeeMembershipPK.membershipInformation.memType
								+ " </div></td><td id='de'>"
								+data[i].description + "<br><div id='since'>" + data[i].since
								+ "</div></td></tr>";
						$("#tableEmpMem tbody").append(result);
					}
				},
				error : function() {
// alert("EMPLOYEE ID NOT FOUND!!");
				}

			});
}
