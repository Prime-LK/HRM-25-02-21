//load department based on add deduct type (active only)
function loaddrpartmentReport() {
	
	var x = document.getElementById("depID");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails1",
		data: {"depID" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails1?depID=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//load department based on add deduct type (active inactive both)
function loadinactiveEmpdepartmentReport() {
	
	var x = document.getElementById("depID");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails1ActiveAndInactive",
		data: {"depID" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails1ActiveAndInactive?depID=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//load active employee based on location
function loadinactiveEmplocationReport() {
	
	var x = document.getElementById("loid");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails2",
		data: {"loid" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails2?loid=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}


//load active and inactive employee based on location
function loadinallEmplocationReport() {
	
	var x = document.getElementById("loid");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails2locarion",
		data: {"loid" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails2locarion?loid=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//load active  employee based on category
function loadempCategoryReport() {
	
	var x = document.getElementById("catgoryID");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails3",
		data: {"catgoryID" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails3?catgoryID=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//loadall employee based on category
function loadinallEmpCategoryReport() {
	
	var x = document.getElementById("catgoryID");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails3all",
		data: {"catgoryID" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails3all?catgoryID=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

//load active  employee based on type
function loadempTypeReport() {
	
	var x = document.getElementById("tid");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails4",
		data: {"tid" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails4?tid=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}


//load all  employee based on type
function loadinallEmpTypeReport() {
	
	var x = document.getElementById("tid");
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails4",
		data: {"tid" : x.value , "deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails4?tid=" + x.value+ "&deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}


//load all  employee based on type
function t() {
	
	
	var y = document.getElementById("deductTypeCode");
	
	
	$.ajax({
		type: "GET",
		url: "submitEmpSalaryDetails5",
		data: {"deductTypeCode" : y.value},
		success:function(data) {
			window.location.href = "submitEmpSalaryDetails5?deductTypeCode=" + y.value;
			
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

function validateDropDown() {
	if (document.getElementById("inlineRadio1").checked) {
		document.getElementById("dept").disabled = false;
	} else if (document.getElementById("inlineRadio2").checked) {
		document.getElementById("loc").disabled = false;
	} else if (document.getElementById("inlineRadio3").checked) {
		document.getElementById("cat").disabled = false;
	} else if (document.getElementById("inlineRadio4").checked) {
		document.getElementById("typ").disabled = false;
	} 

}

function validateSelectField() {
	document.getElementById("dept").disabled = true;
	document.getElementById("loc").disabled = true;
	document.getElementById("cat").disabled = true;
	document.getElementById("typ").disabled = true;
	
}