		function loadGrid() {

			var x = document.getElementById("empID").value;
			$("#tableEmpSkill tbody").empty();

			$.ajax({
				type : 'GET',
				url : "getSkillDtails",
				data : {
					"empID" : x
				},
				success : function(data) {

					$("#tableEmpSkill tbody").empty();
					for (var i = 0; i < data.length; i++) {

//						var result = "<tr><td id='editSkill'> "
//						+ "<a href=updateEmpSkill?empID="
//						+ data[i].skillPK.empID.empID + "&sid="
//						+ data[i].skillPK.sid.sid
//						+ "><img src='resources/img/edit.png' width='25px' height='25px'></a>"
//						+ "</td><td><div  id='skill'>" + data[i].skillPK.sid.skill
//								+ " </div></td><td><div id='profeciency'> " + data[i].sProficiency
//								+ " </div></td></tr>";
//						$("#tableEmpSkill tbody").append(result);
						
						var result = "<tr><td>"
							+ data[i].skillPK.sid.skill
							+ "</td>"
							+ "<td>"
							+ data[i].sProficiency
							+ "</td>"
							+ "<td><a href=updateEmpSkill?empID="
					    	+ data[i].skillPK.empID.empID + "&sid="
						    + data[i].skillPK.sid.sid
						    + "><i class='far fa-edit'></i></a></td></tr>";
					$("#tableEmpSkill tbody").append(result);						
					}
				},
				error : function() {
					alert("EMPLOYEE ID NOT FOUND!!");
				}

			});
			
			var x = document.getElementById("empID").value;
			$("#tableEmpLan tbody").empty();

			$.ajax({
				type : 'GET',
				url : "getlanguageDtails",
				data : {
					"empID" : x
				},
				success : function(data) {

					$("#tableEmpLan tbody").empty();
					for (var i = 0; i < data.length; i++) {
//						var result = "<tr><td><a href=updateEmpLa?empID="
//								+ data[i].lanPk.empID.empID + "&languageId="
//								+ data[i].lanPk.lid.lid
//								+ "><img src='resources/img/edit.png' width='25px' height='25px'>" +
//								  "</a></td><td><div id='lan'>" 
//								+ data[i].lanPk.lid.language
//								+ " </div></td><td><div id='desc'> " + data[i].desc + " </div></td>"
//								+ "</tr>";
//						$("#tableEmpLan tbody").append(result);
						
						var result = "<tr><td>"
							+ data[i].lanPk.lid.language
							+ "</td>"
							+ "<td>"
							+ data[i].desc
							+ "</td>"
							+ "<td><a href=updateEmpLa?empID="
							+ data[i].lanPk.empID.empID + "&languageId="
							+ data[i].lanPk.lid.lid
							+ "><i class='far fa-edit'>" +
						     "</a></td></tr>";
					$("#tableEmpLan tbody").append(result);	
	
					}
				},
				error : function() {
					alert("EMPLOYEE ID NOT FOUND!!");
				}

			});
			
			var x = document.getElementById("empID").value;
			$("#tableEmpAtc tbody").empty();
			
			$.ajax({
				type : 'GET',
				url : "getActivityDtails",
				data : {
					"empID" : x
				},
				success : function(data) {

					$("#tableEmpAtc tbody").empty();
					for (var i = 0; i < data.length; i++) {

//						var result = "<tr><td> "
//								+ "<a href=updateEet?empID="
//								+ data[i].actPK.empID.empID + "&actTypeID="
//								+ data[i].actPK.eType.actTypeID
//								+ "><img src='resources/img/edit.png' width='25px' height='25px'></a>"
//								+ "</td><td><div id='act'>" + data[i].actPK.eType.type
//								+ " </div></td><td><div id='actDetails'> " + data[i].act + " </div></td></tr>";
//						$("#tableEmpAtc tbody").append(result);
						
						var result = "<tr><td>"
							+ data[i].actPK.eType.type
							+ "</td>"
							+ "<td>"
							+ data[i].act
							+ "</td>"
							+ "<td><a href=updateEet?empID="
							+ data[i].actPK.empID.empID + "&actTypeID="
							+ data[i].actPK.eType.actTypeID
							+ "><i class='far fa-edit'></a></td></tr>";
					$("#tableEmpAtc tbody").append(result);
					}
				},
				error : function() {
					alert("EMPLOYEE ID NOT FOUND!!");
				}

			});

		}

//function loadGrid() {
////	alert("Hello World");
//	var x = document.getElementById("empID").value;
//	$("#tblCInfo tbody").empty();
//	
//	$.ajax({
//		type: "GET",
//		url: "getEmp",
//		data:{"empID" : x},
//		success:function(data) {
//			$("tblCInfo tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td>"
//						+ data[i].employeeContactInfoPK.employeeContactType.contactType
//						+ " </td><td> "
//						+ data[i].contactNo
//						+ " </td><td> "
//						+"<a href=updateEAdd?empID="
//						+ data[i].employeeContactInfoPK.empID.empID 
//						+"&fieldId="
//						+data[i].employeeContactInfoPK.employeeContactType.cTypeID+
//						"><i class='fas fa-pen'></i></a>"
//						
//						+ "</td></tr>";
//				$("#tblCInfo tbody").append(result);
//			}
//		},error:function() {
//			alert("No ID Found");
//		}
//	});
//}