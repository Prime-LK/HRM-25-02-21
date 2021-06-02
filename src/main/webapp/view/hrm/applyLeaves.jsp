<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
	<%@include file="../../WEB-INF/jsp/head.jsp"%>
	
</head>
<body>
	<div class="wrapper">
		<div class="main-header">
			<!-- Logo Header -->
				<%@include file="../../WEB-INF/jsp/logoHeader.jsp"%>
			<!-- End Logo Header -->
			<!-- Navbar Header -->
				<%@include file="../../WEB-INF/jsp/navbar.jsp"%>
			<!-- End Navbar -->
		</div>
		<!-- Sidebar -->
			<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl col-lg">
								 <h2 class="text-white pb-2 fw-bold">Apply for Leave</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow mb-4 border-left-primary">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl mb-4">

					                	<c:if test = "${success ==1}">
											<div class="alert alert-success alert-dismissible">
											  <button type="button" class="close" data-dismiss="alert">&times;</button>
											  <strong>Success!</strong> Data Successfully Saved.
											</div>
										</c:if>
										<c:if test = "${success ==0}">
										  <div class="alert alert-danger alert-dismissible">
										    <button type="button" class="close" data-dismiss="alert">&times;</button>
										    <strong>Warning!</strong>Something went wrong ! Please try again!
										  </div>
										</c:if>
										
										<form:form action="applyLeave" method="post" modelAttribute="applyleave" id="form1">
										
											<form:input type="hidden" path="leaveID"/>
											<form:input type="hidden" path="company.comID"/>
											
											
					                		<div class="form-group row">
												<div class="col-lg">
													<label>Department</label>
													<form:select class="form-control form-control-sm" id="department"
														path="department.depID" required="">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${DepAll}" var="dp">
															<form:option value="${dp.depID}">${dp.department}</form:option>
														</c:forEach>
													</form:select>

												</div>

											</div>
											<div class="form-group row">
												<div class="col-lg">
													<label>Employee</label>
													<form:select class="form-control form-control-sm" id="employee"
														path="employee.empID" required="" onchange="getAppliedLeave(this.value);getBalanceLeaveMsg();getBalanceLeaveSum()">
														<form:option value="">--Select--</form:option>
														<c:forEach items="${EmpAll}" var="emp">
															<form:option value="${emp.empID}">${emp.name} ${emp.lastname}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg">
													<label>Leave Type</label>
													<form:select class="form-control form-control-sm" id="leaveType"
														path="leaveType.leaveCode" required="" onchange="getBalanceLeaveMsg();getBalanceLeaveSum()">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${leaveAll}" var="l">
															<form:option value="${l.leaveCode}">${l.leaveType}</form:option>
														</c:forEach>
													</form:select>
													
													<div class="alert alert-warning alert-dismissible" id="validateMsgBox" style="display:none">
													  <strong>Info!</strong> <div id="validateMsg"><span></span></div>
													</div>
							
												</div>
											</div>						

										</form:form>			                

									</div>
									<div class="col-xl mb-4">
									
											<div class="form-group row">
												<div class="col-lg-4">
													<label>Full / Half</label>
													<select class="form-control form-control-sm" id="type"
														name="type" onchange="getBalanceLeaveSum()" form="form1" required>
														<option value="Full" selected>Full</option>
														<option value="Half">Half</option>
													</select>
												</div>
											</div>
											
											<div class="form-group row">
												<div class="col-lg">
													
													<div id="dateTimePicker"></div>
													<input type="hidden" name="dates" id="my_hidden_input" form="form1">
													
												</div>
											</div>
																			
									</div>
									<div class="col-xl mb-4">
											<div class="form-group row">
												<div class="col-lg">
													<label>Remarks</label>
													<textarea class="form-control" id="dec" name="desc" form="form1"></textarea>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg">
													<input type="submit" class="btn btn-success btn-sm" value="Apply Leave" form="form1">
													<input type="reset" class="btn btn-warning btn-sm" value="Clear" form="form1">
												</div>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xl">
										<div class="table-responsive">
											<table class="table" id="table1">
												<thead class="thead-light">
													<tr>
														<td></td>
														<th>Leave Type</th>													
														<th>Full / Half</th>
														<th>Day(s)</th>
														<th>Date(s)</th>
														<th>Approved</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
									</div>
								</div>

			                </div>
			              </div>
			            
					</div>	
				</div>
				
			</div>	
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

<script>

function getAppliedLeave(str)
{
	if (str=="") {
       	$("#table1 tbody").empty();
       	return;
       	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getAppliedLeavesByEmployee",
		    data: {"employeeID" : str},
		    success: function(data){

		    	$("#table1 tbody").empty();
				for(var i=0; i<data.length; i++){
					
					if(data[i].approved == true)
						var markup = "<tr class='table-success'><td>"+data[i].createTime+"</td><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].type+"</td><td>" + data[i].days + "</td><td>"+data[i].dates+"</td><td>Yes</td></tr>";
					else
						var markup = "<tr class='table-warning'><td>"+data[i].createTime+"</td><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].type+"</td><td>" + data[i].days + "</td><td>"+data[i].dates+"</td><td>Pending</td></tr>";
		       		 
					
					$("#table1 tbody").append(markup);
		       	 }

		    },
		    error:function(){
		        alert("error");
		    }
		
		});
	}
}

$(document).ready(function(){
	
    // Initialize select2
    //$("#employee").select2();
   
});

function getBalanceLeaveMsg() {
	
	var employeeID = document.getElementById("employee").value;
	var leaveTypeID = document.getElementById("leaveType").value;
	
	if (leaveTypeID=="" || employeeID=="") {
       	//$("table tbody").empty();
       	return;
       	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getBalanceLeaves",
		    data: {"employeeID":employeeID, "leaveTypeID":leaveTypeID},
		    success: function(data){
		       	 
				$('#validateMsg span').text(data);
				document.getElementById("validateMsg").style.fontSize = "small";
				document.getElementById("validateMsgBox").style.display = "block";

		    },
		    error:function(){
		        alert("error");
		    }
		
		});
	}
}


function getBalanceLeaveSum() {
	
	var employeeID = document.getElementById("employee").value;
	var leaveTypeID = document.getElementById("leaveType").value;
	var type = document.getElementById("type").value;
	
	
	if (leaveTypeID=="" || employeeID=="" || type=="") {	
       	return;  	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getBalanceLeaveSum",
		    data: {"employeeID":employeeID, "leaveTypeID":leaveTypeID},
		    success: function(data){
		    	if(type=="Half"){
		    		data = data*2;
		    	}
		    	  var new_options = {
		    			    format: "dd/mm/yyyy",
		    			    todayBtn: "linked",
		    			    multidate: data,
		    			    multidateSeparator: ",",
		    			    daysOfWeekDisabled: "0,6",
		    			    daysOfWeekHighlighted: "0,6",
		    			    todayHighlight: true
		    			  }
    			  // Destroy previous datepicker
    			  $('#dateTimePicker').datepicker('destroy');
    			  // Re-int with new options
    			  $('#dateTimePicker').datepicker(new_options);

		    },
		    error:function(){
		        alert("error");
		    }
		
		});
	}
}


$('#dateTimePicker').datepicker({
    format: "dd/mm/yyyy",
    todayBtn: "linked",
    multidate: true,
    multidateSeparator: ",",
    daysOfWeekDisabled: "0,6",
    daysOfWeekHighlighted: "0,6",
    todayHighlight: true
});



$('#dateTimePicker').on('changeDate', function() {
    $('#my_hidden_input').val(
        $('#dateTimePicker').datepicker('getFormattedDate')
    );
})

</script>



</body>
</html>