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

			              <div class="card shadow">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl">

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
											<form:input type="hidden" path="company.comID" id="companyID"/>
											
											
					                		<div class="form-group row">
												<div class="col-lg">
													<label for="department">Department</label>
													<form:select class="form-control form-control-sm" id="department"
														path="department.depID" required="" onchange="loadEmployeeByDepartment()">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${allDepartment}" var="dp">
															<form:option value="${dp.depID}">${dp.department}</form:option>
														</c:forEach>
													</form:select>

												</div>

											</div>
											<div class="form-group row">
												<div class="col-lg">
													<label for="employee">Employee</label>
													<form:select class="form-control form-control-sm" id="employee"
														path="employee.empID" required="" onchange="getAppliedLeave(this.value);getBalanceLeaveMsg();getBalanceLeaveSum()">
														<form:option value="">--Select--</form:option>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg">
													<label for="leaveType">Leave Type</label>
													<form:select class="form-control form-control-sm" id="leaveType"
														path="leaveType.leaveTypeID" required="" onchange="getBalanceLeaveMsg();getBalanceLeaveSum()">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${allLeaveType}" var="l">
															<form:option value="${l.leaveTypeID}">${l.leaveType}</form:option>
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
													<label for="fullHalf">Full / Half</label>
													<select class="form-control form-control-sm" id="fullHalf"
														name="fullHalf" onchange="getBalanceLeaveSum()" form="form1" required>
														<option value="Full" selected>Full</option>
														<option value="Half">Half</option>
													</select>
												</div>
											</div>
											
											<div class="form-group row">
												<div class="col-lg">
													
													<div id="dateTimePicker"></div>
													<input type="hidden" name="date" id="my_hidden_input" form="form1">
													
												</div>
											</div>
																			
									</div>
									<div class="col-xl mb-4">
											<div class="form-group row">
												<div class="col-lg">
													<label>Remarks</label>
													<textarea class="form-control" id="dec" name="remark" form="form1"></textarea>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg">
													<input type="submit" class="btn btn-success btn-sm" value="Apply Leave" form="form1">
													<input type="reset" class="btn btn-danger btn-sm" value="Reset" form="form1">
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
														<th>Leave Type</th>													
														<th>Full / Half</th>
														<th>Date</th>
														<th>Remarks</th>														
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
	var companyID = document.getElementById("companyID").value;
	
	if (str=="" || companyID=="") {
       	$("#table1 tbody").empty();
       	return;
       	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getAppliedLeaveByEmployee",
		    data: {"employeeID" : str, "companyID":companyID},
		    success: function(data){

		    	$("#table1 tbody").empty();
				for(var i=0; i<data.length; i++){
					
					if(data[i].approved == true)
						var markup = "<tr class='table-success'><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].fullHalf+"</td><td>"+data[i].date+"</td><td>"+data[i].remark+"</td><td>Yes</td></tr>";
					else
						var markup = "<tr class='table-warning'><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].fullHalf+"</td><td>"+data[i].date+"</td><td>"+data[i].remark+"</td><td>Pending</td></tr>";
		       		 
					
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
	var companyID = document.getElementById("companyID").value;
	
	if (leaveTypeID=="" || employeeID=="" || companyID=="") {
       	//$("table tbody").empty();
       	return;
       	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getBalanceLeaves",
		    data: {"employeeID":employeeID, "leaveTypeID":leaveTypeID, "companyID":companyID},
		    success: function(data){
		       	 
				$('#validateMsg span').text(data);
				document.getElementById("validateMsg").style.fontSize = "small";
				document.getElementById("validateMsgBox").style.display = "block";

		    },
		    error:function(){
		        alert("System error, please try again later !");
		    }
		
		});
	}
}


function getBalanceLeaveSum() {
	
	var companyID = document.getElementById("companyID").value;
	var employeeID = document.getElementById("employee").value;
	var leaveTypeID = document.getElementById("leaveType").value;
	var type = document.getElementById("fullHalf").value;
	
	
	if (leaveTypeID=="" || employeeID=="" || type=="" || companyID=="") {	
       	return;  	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getBalanceLeaveSum",
		    data: {"employeeID":employeeID, "leaveTypeID":leaveTypeID, "companyID":companyID},
		    success: function(data){
		    	if(type=="Half"){
		    		data = data*2;
		    	}
		    	  var new_options = {
		    			    format: "yyyy-mm-dd",
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
		        alert("System error, please try again later !");
		    }
		
		});
	}
}


$('#dateTimePicker').datepicker({
    format: "yyyy-mm-dd",
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

function loadEmployeeByDepartment(){

	var depID = document.getElementById("department").value;
	
	if (depID=="") {
		var dropDown = $('#employee'), option="";
		dropDown.empty();
		return;
	}
	else{
		$.ajax({
        type: 'GET',
        url: "getEmployeesByDepID",
        data: {"depID" : depID},
        success: function(data){

			var dropDown=$('#employee'), option="";
			dropDown.empty();
			selected_option = "<option>--Select--</option>"
			dropDown.append(selected_option);

            for(var i=0; i<data.length; i++){
                option = option + "<option value='"+data[i].detailsPK.empID.empID+ "'>"+data[i].detailsPK.empID.name+" "+data[i].detailsPK.empID.lastname+ "</option>";
            }
            dropDown.append(option);
        },
        error:function(){
            alert("System error, please try again later !");
        }

    	});
	}
}

</script>



</body>
</html>