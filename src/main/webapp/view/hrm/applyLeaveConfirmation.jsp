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
								 <h2 class="text-white pb-2 fw-bold">Leave Applied for Approval</h2>
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
					                
											<form action="updateApprovedStatus" method="POST" id="form1">
		
						                		<div class="form-group row">
													<div class="col-lg">
														<label>Department</label>
														<select class="form-control form-control-sm" id="department"
															name="departmentID" required onchange="loadEmployeeByDepartment()">
															<option value="" selected>--Select--</option>
															<c:forEach items="${allDepartment}" var="dp">
																<option value="${dp.depID}">${dp.department}</option>
															</c:forEach>
														</select>
	
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg">
														<label>Employee</label>
														<select class="form-control form-control-sm" id="employeeSelect"
															name="employeeID" required onchange="getAppliedLeave(this.value)">
															<option value="">--Select--</option>
															<c:forEach items="${EmpAll}" var="emp">
																<option value="${emp.empID}">${emp.name} ${emp.lastname}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group row">
													<div class="col-lg">
														<label>Leave Applied (Pending)</label>
														<select class="form-control form-control-sm" id="applyLeaveSelect"
															name="applyLeaveID" required>

														</select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-lg">
    													<input type="checkbox" id="exampleCheck1" name="approved" value="true">
    													<label for="exampleCheck1">Approved</label>
													</div>
												</div>


												<div class="form-group row">
													<div class="col-lg">
														<input type="submit" class="btn btn-success btn-sm" value="Save Approval">
														<input type="reset" class="btn btn-danger btn-sm" value="Reset">
													</div>
												</div>

		
											</form>
									</div>
									<div class="col-xl mb-4">
										<div class="table-responsive">
											<table class="table">
												<thead>
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

function loadEmployeeByDepartment(){

	var depID = document.getElementById("department").value;
	
	if (depID=="") {
		var dropDown = $('#employeeSelect'), option="";
		dropDown.empty();
		return;
	}
	else{
		$.ajax({
        type: 'GET',
        url: "getEmployeesByDepID",
        data: {"depID" : depID},
        success: function(data){

			var dropDown=$('#employeeSelect'), option="";
			dropDown.empty();
			selected_option = "<option>--Select--</option>"
			dropDown.append(selected_option);

            for(var i=0; i<data.length; i++){
            	option = option + "<option value='"+data[i].detailsPK.empID.empID+ "'>"+data[i].epfNo+ " - " +data[i].detailsPK.empID.name+" "+data[i].detailsPK.empID.lastname+"</option>";
            }
            dropDown.append(option);
        },
        error:function(){
            alert("System error, please try again later !");
        }

    	});
	}
}


function getAppliedLeave(str)
{
	if (str=="") {
	 	$("table tbody").empty();
		var dropDown = $('#applyLeaveSelect'), option="";
		dropDown.empty();
        selected_option = "<option value='' selected>--Select--</option>"
        dropDown.append(selected_option);
        
       	return;
       	
	}else{
			$.ajax({
		    type: 'GET',
		    url: "getAppliedLeaveByEmployee",
		    data: {"employeeID" : str},
		    success: function(data){
				
				var dropDown=$('#applyLeaveSelect'), option="";
				dropDown.empty();
				selected_option = "<option value=''>--Select--</option>"
				dropDown.append(selected_option);
	
	            for(var i=0; i<data.length; i++){
	            	if(data[i].approved == false)
	                	option = option + "<option value='"+data[i].leaveID+"'>"+data[i].leaveType.leaveType+" "+data[i].fullHalf+" "+data[i].date+"</option>";
	            }
	            dropDown.append(option);
	            
		    	$("table tbody").empty();
				for(var i=0; i<data.length; i++){
					
					if(data[i].approved == true)
						var markup = "<tr class='table-success'><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].fullHalf+"</td><td>"+data[i].date+"</td><td>"+data[i].remark+"</td><td>Yes</td></tr>";
					else
						var markup = "<tr class='table-warning'><td>"+data[i].leaveType.leaveType+"</td><td>"+data[i].fullHalf+"</td><td>"+data[i].date+"</td><td>"+data[i].remark+"</td><td>Pending</td></tr>";
		       		 				
					$("table tbody").append(markup);
		       	 }

		    },
		    error:function(){
		        alert("error");
		    }
		
		});
	}
}

</script>

</body>
</html>