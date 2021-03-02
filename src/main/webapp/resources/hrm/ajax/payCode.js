//get data based on periodID
function loadPeriodData() {
	var y = document.getElementById("payPeriodID");
	$.ajax({
		type: "GET",
		url: "loadDataBasedOnpayPeriodID",
		data: {"payPeriodID" : y.value},
		success:function(data) {
			document.getElementById("startDate").value=data.startDate;	
			document.getElementById("endDate").value=data.endDate;	
			document.getElementById("status").value=data.status;
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

// load saved data based on periodID
function loadSavedData() {
	var y = document.getElementById("payPeriodID");
	$.ajax({
		type: "GET",
		url: "loadDataToGrid",
		data: {"payPeriodID" : y.value},
		success:function(data) {
			$("#tablePayCode tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" + data[i].payCode + "</td><td>"
						+ data[i].startDate + "</td><td>" + data[i].endDate
						+ "</td><td>" + data[i].remarks + "</td><td>"
						+ data[i].status + "</td><td><a href=updatePayCode?payCodeID="
						+ data[i].payCodeID+ "><i class='far fa-edit'></i></td></tr>";	       
				$("#tablePayCode tbody").append(result);
			}	
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
}

// get payperiod ID using dates
function loadpayperiodfromdates() {
	var x = document.getElementById("startDate");
	var y = document.getElementById("endDate");
	
	
	$.ajax({
		type: "GET",
		url: "loadPeriodID",
		data: {"endDate" : y.value , "startDate" : x.value},
		success:function(data) {
			document.getElementById("payPeriodID").value= data.payPeriodID;
			document.getElementById("status").value= data.status;
			
			if(data.payPeriodID == undefined){
				alert("Dont have valid payPeriodID");
			}
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
	
	$.ajax({
		type: "GET",
		url: "loadPeriodlist",
		data: {"endDate" : y.value , "startDate" : x.value},
		success:function(data) {
			
			$("#tablePayCode tbody").empty();
			
			
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td>" + data[i].payCode + "</td><td>"
						+ data[i].startDate + "</td><td>" + data[i].endDate
						+ "</td><td>" + data[i].remarks + "</td><td>"
						+ data[i].status + "</td><td><a href=updatePayCode?payCodeID="
						+data[i].payCodeID+  "><i class='far fa-edit'></i></a></td></tr>";
								
				       
				$("#tablePayCode tbody").append(result);
			}
			
		},
		error:function(e) {
			alert("ID Does not Exists");
		}
	});
	
}


// validations-----

$("#payCodeform").submit(function(e) {
    e.preventDefault();
});

function validateForm() {
  
  var payPeriodID = document.getElementById("payPeriodID").value;
  var startDate = document.getElementById("startDate").value;
  var endDate = document.getElementById("endDate").value;
  var payCode = document.getElementById("payCode").value;
  var remarks = document.getElementById("remarks").value;
  var status = document.getElementById("status").value;

  
  
  if(payPeriodID=="")
  {
      document.getElementById("div1").innerHTML="Enter Pay PeriodID";
      document.getElementById("div1").style.color="Red";
     
  }
  else
  {
      document.getElementById("div1").innerHTML="";
  } 
  
  if(startDate=="")
  {
      document.getElementById("div2").innerHTML="Enter Start Date";
      document.getElementById("div2").style.color="Red";
      
  }
  else
  {
      document.getElementById("div2").innerHTML="";
  } 
  
  if(endDate=="")
  {
      document.getElementById("div3").innerHTML="Enter End Date";
      document.getElementById("div3").style.color="Red";
      
  }
  else
  {
      document.getElementById("div3").innerHTML="";
  }
  
  if(payCode=="")
  {
      document.getElementById("div4").innerHTML="Enter Pay Code";
      document.getElementById("div4").style.color="Red";
      
  }
  else
  {
      document.getElementById("div4").innerHTML="";
  } 
  
  if(remarks=="")
  {
      document.getElementById("div5").innerHTML="Enter Remarks";
      document.getElementById("div5").style.color="Red";
      
  }
  else
  {
      document.getElementById("div5").innerHTML="";
  } 
  
  if(status=="")
  {
      document.getElementById("div6").innerHTML="Enter Status";
      document.getElementById("div6").style.color="Red";
      
  }
  else
  {
      document.getElementById("div6").innerHTML="";
  } 
  
}
