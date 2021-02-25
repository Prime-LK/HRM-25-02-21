function loadRelatedDep() {
//	var x = document.getElementById("loc").value;
	var y = document.getElementById("depID").value;
//	console.log(y);
	$.ajax({
		type: "GET",
		url: "loadGrip",
		data: {"dep": y},
		success:function(data) {
			$("#talbeEmpRe tbody").empty();
//			alert(data);
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" 
					  		 + data[i].detailsPK.empID.empID + "</td><td>" 
					  		 + data[i].detailsPK.empID.name + " " +data[i].detailsPK.empID.lastname + "</td><td>" 
					  		 + data[i].detailsPK.empID.contact_num1 + "</td><td>" 
					  		 + data[i].detailsPK.empID.email + "</td><td>" 
					  		 + data[i].detailsPK.empID.dob + 
					  		 "</td></tr>";
				$("#talbeEmpRe tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
}


function loadRelatedEmpusingJoinedDate() {

	var y = document.getElementById("joinedDate").value;
	var x = document.getElementById("joinedDate2").value;
	 
	$.ajax({
		type: "GET",
		url: "loadGrid",
		data: {"joinedDate": y , "joinedDate2": x },
		success:function(data) {
			$("#talbeEmpRe tbody").empty();
//			alert(data);
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" 
					  		 + data[i].detailsPK.empID.empID + "</td><td>" 
					  		 + data[i].detailsPK.empID.name + " " +data[i].detailsPK.empID.lastname + "</td><td>" 
					  		 + data[i].detailsPK.empID.contact_num1 + "</td><td>" 
					  		 + data[i].detailsPK.empID.email + "</td><td>" 
					  		 + data[i].detailsPK.empID.dob + 
					  		 "</td></tr>";
			
				$("#talbeEmpRe tbody").append(result);
				
			}
		},
		error:function(e) {
			alert("Not Found Employees");
		}
	});
}
//get report based on joined date 
function myFunction() {

	var y = document.getElementById("joinedDate");
	var x = document.getElementById("joinedDate2");
	
	$.ajax({
		type: "GET",
		url: "submitjoinedDateBasedReport",
		data: {"joinedDate" : y.value ,"joinedDate2" : x.value },
		success:function(data) {
				
				window.location.href = "submitjoinedDateBasedReport?joinedDate=" + y.value+ "&joinedDate2=" + x.value;
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//get relevant employee based on resigned date
function loadRelatedEmpusingresignDate() {
	var y = document.getElementById("resignDate").value;
	var x = document.getElementById("resignDate2").value;
	
	
	$.ajax({
		type: "GET",
		url: "resigndateBasedloadGrid",
		data: {"resignDate": y , "resignDate2": x },
		success:function(data) {
			$("#talbeEmpRe tbody").empty();
//			alert(data);
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" 
					  		 + data[i].detailsPK.empID.empID + "</td><td>" 
					  		 + data[i].detailsPK.empID.name + " " +data[i].detailsPK.empID.lastname + "</td><td>" 
					  		 + data[i].detailsPK.empID.contact_num1 + "</td><td>" 
					  		 + data[i].detailsPK.empID.email + "</td><td>" 
					  		 + data[i].detailsPK.empID.dob +
					  		 "</td></tr>";
				$("#talbeEmpRe tbody").append(result);
			}
		},
		error:function(e) {
			alert("Not Found Employees");
		}
	});
}

//get relevant employee based on resign date to report 
function myFunction2() {

	var y = document.getElementById("resignDate");
	var x = document.getElementById("resignDate2");
	
	$.ajax({
		type: "GET",
		url: "submitresignDateBasedReport",
		data: {"resignDate" : y.value ,"resignDate2" : x.value },
		success:function(data) {
				
				window.location.href = "submitresignDateBasedReport?resignDate=" + y.value+ "&resignDate2=" + x.value;
			
		},
		error:function(e) {
			alert("Employee Does not Exists");
		}
	});
}
